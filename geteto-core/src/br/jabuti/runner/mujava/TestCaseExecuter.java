package br.jabuti.runner.mujava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Vector;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import mujava.MutationSystem;
import mujava.test.JMutationLoader;
import mujava.test.NoMutantDirException;
import mujava.test.NoMutantException;
import mujava.test.OriginalLoader;
import mujava.test.TestResult;
import mujava.util.Debug;
import mujava.util.MutantDirFilter;

public class TestCaseExecuter
{
  Object lockObject = new Object();
  Object[] original_results;
  boolean rescol = false;
  int TIMEOUT = 3000;
  final int MAX_TRY = 100;
  Class original_executer;
  Object original_obj;
  Object mutant_result;
  public Vector previously_killed;
  public Vector persistent;
  Class mutant_executer;
  volatile Object mutant_obj;
  Method[] testCases;
  volatile Method testcase;
  Method setup;
  Method teardown;
  String whole_class_name;
  String testSet;
  volatile boolean mutantRunning = true;
  Vector active_mutants = new Vector();

  public TestCaseExecuter(String targetClassName)
  {
    int index = targetClassName.lastIndexOf(".");
    if (index < 0)
      MutationSystem.CLASS_NAME = targetClassName;
    else {
      MutationSystem.CLASS_NAME = targetClassName.substring(index + 1, targetClassName.length());
    }

    MutationSystem.DIR_NAME = targetClassName;
    MutationSystem.CLASS_MUTANT_PATH = MutationSystem.MUTANT_HOME + "/" + targetClassName + 
      "/" + MutationSystem.CM_DIR_NAME;
    MutationSystem.TRADITIONAL_MUTANT_PATH = MutationSystem.MUTANT_HOME + "/" + targetClassName + 
      "/" + MutationSystem.TM_DIR_NAME;
    MutationSystem.EXCEPTION_MUTANT_PATH = MutationSystem.MUTANT_HOME + "/" + targetClassName + 
      "/" + MutationSystem.EM_DIR_NAME;

    this.whole_class_name = targetClassName;
  }

  public void setTimeOut(int msecs)
  {
    this.TIMEOUT = msecs;
  }

  public void setPreviouslyKilledMutants(String[] mutlist)
  {
    this.previously_killed = new Vector();
    for (int i = 0; i < mutlist.length; i++)
      this.previously_killed.add(mutlist[i]);
  }

  public boolean readTestSet(String testSetName)
  {
    try {
      this.testSet = testSetName;

      OriginalLoader myLoader = new OriginalLoader();
      this.original_executer = myLoader.loadTestClass(this.testSet);
      this.original_obj = this.original_executer.newInstance();

      if (this.original_obj == null) {
        System.out.println(" Can't instantiace original object");
        return false;
      }

      this.testCases = this.original_executer.getDeclaredMethods();
      if (this.testCases == null) {
        System.out.println(" No test cases exist ");
        return false;
      }
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
      return false;
    }
    return true;
  }

  boolean sameResult(Object result1, Object result2)
  {
    if ((result1 == null) && (result2 == null))
      return true;
    if ((result1 == null) || (result2 == null)) {
      return false;
    }
    return result1.toString().equals(result2.toString());
  }

  public TestResult runClassMutants()
    throws NoMutantException, NoMutantDirException
  {
    MutationSystem.MUTANT_PATH = MutationSystem.CLASS_MUTANT_PATH;
    TestResult test_result = new TestResult();
    runMutants(test_result);
    return test_result;
  }

  public TestResult runExceptionMutants() throws NoMutantException, NoMutantDirException {
    MutationSystem.MUTANT_PATH = MutationSystem.EXCEPTION_MUTANT_PATH;
    TestResult test_result = new TestResult();
    runMutants(test_result);
    return test_result;
  }

  public TestResult runTraditionalMutants(String methodSignature) throws NoMutantException, NoMutantDirException
  {
    MutationSystem.MUTANT_PATH = MutationSystem.TRADITIONAL_MUTANT_PATH;
    String original_mutant_path = MutationSystem.MUTANT_PATH;

    TestResult test_result = new TestResult();

    if (methodSignature.equals("All method"))
    {
      try {
        File f = new File(MutationSystem.TRADITIONAL_MUTANT_PATH, "method_list");
        FileReader r = new FileReader(f);
        BufferedReader reader = new BufferedReader(r);

        String readSignature = reader.readLine();
        while (readSignature != null) {
          MutationSystem.MUTANT_PATH = original_mutant_path + "/" + readSignature;
          System.out.println("=+=+=+=+=+= METHOD: " + readSignature + " =+=+=+=+=+=");
          try {
            runMutants(test_result);
          } catch (NoMutantException localNoMutantException) {
          }
          readSignature = reader.readLine();
        }
        reader.close();
      } catch (Exception e) {
        System.err.println("Error in update() in TraditioanlMutantsViewerPanel.java");
      }
    } else {
      MutationSystem.MUTANT_PATH = original_mutant_path + "/" + methodSignature;
      runMutants(test_result);
    }
    return test_result;
  }

  void runMutants(Object mutant, Method testcase)
    throws InterruptedException
  {
    boolean isjUnit = mutant instanceof TestCase;
    this.mutantRunning = true;

    if (isjUnit)
    {
      try
      {
        try
        {
          Method setup2 = this.mutant_executer.getMethod("setUp", null);
          if (setup2 != null)
            setup2.invoke(this.mutant_obj, null);
        }
        catch (Exception localException1) {
        }
        testcase.invoke(this.mutant_obj, null);
        this.mutant_result = new String("true");
      }
      catch (AssertionFailedError af)
      {
        this.mutant_result = af.toString();
      }
      catch (Exception e)
      {
        Method teardown2;
        this.mutant_result = (e.getCause().getClass().getName() + " : " + e.getCause().getMessage());
      }
      finally
      {
        try
        {
          Method teardown2 = this.mutant_executer.getMethod("tearDown", null);
          if (teardown2 != null)
            teardown2.invoke(this.mutant_obj, null);
        }
        catch (Exception localException4) {
        }
        this.mutantRunning = false;
        synchronized (this.lockObject) {
          this.lockObject.notify();
        }
      }
      try
      {
        Method teardown2 = this.mutant_executer.getMethod("tearDown", null);
        if (teardown2 != null)
          teardown2.invoke(this.mutant_obj, null);
      }
      catch (Exception localException5) {
      }
      this.mutantRunning = false;
      synchronized (this.lockObject) {
        this.lockObject.notify();
      }

    }

    try
    {
      this.mutant_result = testcase.invoke(this.mutant_obj, null);
    }
    catch (Exception e)
    {
      this.mutant_result = (e.getCause().getClass().getName() + " : " + e.getCause().getMessage());

      this.mutantRunning = false;
      synchronized (this.lockObject) {
        this.lockObject.notify();
      }
    }
    finally
    {
      this.mutantRunning = false;
      synchronized (this.lockObject) {
        this.lockObject.notify();
      }
    }
  }

  synchronized void waitUntilAtLeast(long timeOut)
    throws InterruptedException
  {
    wait(timeOut);
  }

  private TestResult runMutants(TestResult tr)
    throws NoMutantException, NoMutantDirException
  {
    try
    {
      File f = new File(MutationSystem.MUTANT_PATH);
      if (!f.exists()) {
        System.err.println(" There is no directory for the mutants of " + MutationSystem.CLASS_NAME);
        System.err.println(" Please generate mutants for " + MutationSystem.CLASS_NAME);
        throw new NoMutantDirException();
      }

      String[] mutantDirectories = f.list(new MutantDirFilter());
      if ((mutantDirectories == null) || (mutantDirectories.length == 0))
      {
        throw new NoMutantException();
      }

      int mutant_num = mutantDirectories.length;

      for (int i = 0; i < mutant_num; i++) {
        if (this.previously_killed != null)
        {
          if (!this.previously_killed.contains(mutantDirectories[i]))
            active_mutants.add(mutantDirectories[i]);
          else {
            tr.killed_mutants.add(mutantDirectories[i]);
          }
        }
        else {
          active_mutants.add(mutantDirectories[i]);
        }

      }

      String[] killed_mutants = new String[this.testCases.length];
      for (int k = 0; k < this.testCases.length; k++) {
        killed_mutants[k] = "";
      }

      if (!this.rescol)
      {
        this.original_results = new Object[this.testCases.length];
        Debug.println("\n\n==== Generating original results ========================================");
      }

      for (int k = 0; k < this.testCases.length; k++) {
        if (this.rescol)
        {
          System.out.println("///////// SKIP ORIGINALS \\\\\\\\");
          break;
        }

        String testName = this.testCases[k].getName();
        if (!testName.startsWith("test"))
          continue;
        this.testcase = this.original_executer.getMethod(testName, null);
        try
        {
          this.setup = this.original_executer.getMethod("setUp", null);
          this.teardown = this.original_executer.getMethod("tearDown", null);
        }
        catch (NoSuchMethodException localNoSuchMethodException) {
        }
        boolean x = this.testcase.getDeclaringClass().getSuperclass().equals(TestCase.class);
        boolean y = this.testcase.getDeclaringClass().getSuperclass().getSuperclass().equals(TestCase.class);

        if ((x) || (y))
        {
          try
          {
            if (this.setup != null) {
              this.setup.invoke(this.original_obj, null);
            }
            this.testcase.invoke(this.original_obj, null);
            this.original_results[k] = "true";

            if (this.teardown != null) {
              this.teardown.invoke(this.original_obj, null);
            }
            Debug.println("--- Result for " + testName + "  :  " + this.original_results[k]);
          }
          catch (AssertionFailedError af)
          {
            this.original_results[k] = af;
            this.teardown.invoke(this.original_obj, null);
          }
          catch (Exception e)
          {
            this.original_results[k] = (e.getCause().getClass().getName() + " : " + e.getCause().getMessage());
            Debug.println("--- Result for " + testName + "  :  " + this.original_results[k]);
            Debug.println(" [warining] " + testName + " generate exception as a result ");
            e.printStackTrace();
            if (this.teardown == null) continue; 
          }
          this.teardown.invoke(this.original_obj, null);
        }
        else
        {
          try
          {
            this.original_results[k] = this.testcase.invoke(this.original_obj, null);
            Debug.println("Result for " + testName + "  :  " + this.original_results[k]);
          }
          catch (Exception e) {
            this.original_results[k] = (e.getCause().getClass().getName() + " : " + e.getCause().getMessage());
            Debug.println("Result for " + testName + "  :  " + this.original_results[k]);
            Debug.println(" [warining] " + testName + " generate exception as a result ");
          }

        }

      }

      this.rescol = true;
      Debug.println("\n\n***********| Executing Mutants |*********");
      for (int i = 0; i < active_mutants.size(); i++)
      {
        String mutant_name = active_mutants.get(i).toString();
        JMutationLoader mutantLoader = new JMutationLoader(mutant_name);

        this.mutant_executer = mutantLoader.loadTestClass(this.testSet);
        this.mutant_obj = this.mutant_executer.newInstance();
        Debug.print("\n\n----------------------------------------------\n" + mutant_name);
        Debug.print("\n" + (i + 1) + " of " + active_mutants.size());
        Debug.print("\n-----------------------------------------------\n");

        for (int k = 0; k < this.testCases.length; k++) {
          String testName = this.testCases[k].getName();
          try {
            if (!testName.startsWith("test")) continue;
            this.testcase = this.mutant_executer.getMethod(testName, null);

            Runnable r = new Runnable() {
              public void run() {
                try {
                  TestCaseExecuter.this.runMutants(TestCaseExecuter.this.mutant_obj, TestCaseExecuter.this.testcase);
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            };
            Thread t = new Thread(r);
            t.start();

            synchronized (this.lockObject) {
              this.lockObject.wait(this.TIMEOUT);
            }
            if (this.mutantRunning) {
              t.interrupt();
              this.mutant_result = ("time_out: more than " + this.TIMEOUT + " seconds");
            }
          } catch (Exception e) {
            this.mutant_result = (e.getCause().getClass().getName() + " : " + e.getCause().getMessage());
          }

          if (this.mutant_result == null) {
            this.mutant_result = "null";
            Debug.println2(" - " + testName + " = null     ");
            tr.killed_mutants.add(mutant_name);
            killed_mutants[k] = (killed_mutants[k] + mutant_name + " ");
            break;
          }
          Debug.println2(" - " + testName + " = " + this.mutant_result.toString() + "     ");

          if (sameResult(this.original_results[k], this.mutant_result)) {
            continue;
          }
          tr.killed_mutants.add(mutant_name);
          killed_mutants[k] = (killed_mutants[k] + mutant_name + " ");
          break;
        }

        if (!tr.killed_mutants.contains(mutant_name))
        {
          tr.live_mutants.add(mutant_name);
        }
        System.out.println("------------");
        System.out.println(tr.live_mutants.size() + " live, " + 
          tr.killed_mutants.size() + " killed. ");
        mutantLoader = null;
        this.mutant_executer = null;
        System.gc();
      }

      System.out.println("------------------------------\n Analysis of testcases ");
      for (int i = 0; i < killed_mutants.length; i++) {
        System.out.println("  test " + (i + 1) + "  kill  ==> " + killed_mutants[i]);
      }

      for (int i = 0; i < tr.killed_mutants.size(); i++) {
        active_mutants.remove(tr.killed_mutants.get(i));
      }
      for (int j = 0; j < tr.live_mutants.size(); j++) {
        active_mutants.remove(tr.live_mutants.get(j));
      }
      System.out.println("--------------------");
      System.out.println("Live: " + tr.live_mutants.size());
      System.out.println("Dead: " + tr.killed_mutants.size());
      System.out.println("Active: " + active_mutants.size());
      System.out.println("--------------------");

      if (active_mutants.size() > 0)
      {
        System.err.println("There are remaining active mutants!");
        System.exit(1);
      }
    }
    catch (NoMutantException e1) {
      throw e1;
    } catch (NoMutantDirException e2) {
      throw e2;
    } catch (ClassNotFoundException e3) {
      System.err.println("[Execution 1] " + e3);
      e3.printStackTrace();
      return null;
    } catch (Exception e) {
      System.err.println("[Exception 2]" + e);
      e.printStackTrace();
      return null;
    }
    return tr;
  }

  void erase_killed_mutants(Vector v) {
    System.out.println("Deleting directories of killed mutants");
    for (int i = 0; i < v.size(); i++) {
      System.out.print(v.get(i).toString() + " ");
      erase_directory(v.get(i).toString());
    }
  }

  void erase_directory(String mutant_name) {
    File mutant_dir = new File(MutationSystem.MUTANT_PATH + "/" + mutant_name);
    File[] f = mutant_dir.listFiles();
    boolean flag = false;
    for (int i = 0; i < f.length; i++) {
      while (!flag) {
        flag = f[i].delete();
      }
      flag = false;
    }

    while (!flag)
      flag = mutant_dir.delete();
  }
}