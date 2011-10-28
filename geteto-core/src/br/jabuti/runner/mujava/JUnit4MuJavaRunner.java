package br.jabuti.runner.mujava;

import java.io.File;
import java.io.FileWriter;

import mujava.MutationSystem;
import mujava.test.TestResult;
import mujava.util.Debug;

public class JUnit4MuJavaRunner {
  public static final boolean debug = true;

  public static void main(String[] args)
  {
    String syshome = args[0];
    syshome = syshome.substring(0, syshome.length() - 1);
    String source = "/" + args[1];
    String classes = "/" + args[2];
    String result = "/" + args[3];
    String tests = "/" + args[4];
    String whichTest = args[5];

    String classlist = args[6];
    String timeout = args[7];
    int testlevel = Integer.parseInt(args[8]);
    boolean runTrad = testlevel > 1;
    boolean runClas = (testlevel == 1) || (testlevel == 3);

    System.out.println("MuClipse v1.3R2");
    System.out.println("MuClipse is running tests...");

    System.out.println("For project located in:\n" + syshome);
    System.out.println("Source located in: " + source);
    System.out.println("Classes located in: " + classes);
    System.out.println("Results located in: " + result);
    System.out.println("Tests located in: " + tests);
    System.out.println("Test to run: " + whichTest);
    System.out.println("With timeout: " + timeout);
    if (runTrad)
      System.out.println("Will run traditional");
    if (runClas) {
      System.out.println("Will run class");
    }

    MutationSystem.SYSTEM_HOME = syshome;
    MutationSystem.SRC_PATH = source;
    MutationSystem.CLASS_PATH = classes;
    MutationSystem.MUTANT_HOME = result;
    MutationSystem.TESTSET_PATH = tests;
    

    System.out.println("------------------------------- \n Tests complete! Results:");
    String targetClassName = classlist;
    System.out.println("Starting with class: " + targetClassName);

    System.out.println("Final testclass name: " + whichTest);
    Debug.setDebugLevel(3);
    TestResult tr = new TestResult();
    TestResult tr2 = new TestResult();

    if (runTrad)
    {
      System.out.println("Running against traditional mutants...");
      tr = runTest(targetClassName, whichTest, Integer.parseInt(timeout), false);
    }

    if (runClas)
    {
      System.out.println("Running against classlevel mutants...");
      tr2 = runTest(targetClassName, whichTest, Integer.parseInt(timeout), true);
    }
    int live_num2 = tr2.live_mutants.size();
    int killed_num2 = tr2.killed_mutants.size();
    int live_num1 = tr.live_mutants.size();
    int killed_num1 = tr.killed_mutants.size();

    int live_num = live_num1 + live_num2;
    int killed_num = killed_num1 + killed_num2;

    Float mutant_score = new Float(killed_num * 100 / (
      killed_num + live_num));

    System.out.println("-------------------------------");
    System.out.println("Results for class " + targetClassName);
    System.out.println("-------------------------------");
    System.out.println("Live mutants: " + live_num);
    System.out.println("Killed mutants: " + killed_num);
    System.out.println("Mutation Score: " + mutant_score);
    System.out.println("Writing to file...");
    try
    {
      FileWriter data = new FileWriter(syshome + result + "/" + targetClassName + "/testresults.mjv", false);

      data.write("MuJava\n");
      for (int j = 0; j < tr.live_mutants.size(); j++)
      {
        data.write(tr.live_mutants.get(j) + ",");
      }
      for (int j = 0; j < tr2.live_mutants.size(); j++)
      {
        data.write(tr2.live_mutants.get(j) + ",");
      }

      data.write("\n");
      for (int j = 0; j < tr.killed_mutants.size(); j++)
      {
        data.write(tr.killed_mutants.get(j) + ",");
      }
      for (int j = 0; j < tr2.killed_mutants.size(); j++)
      {
        data.write(tr2.killed_mutants.get(j) + ",");
      }

      data.close();
    }
    catch (Exception e) {
      System.err.println("Error writing to file.");
    }
  }

  static TestResult runTest(String targetClassName, String testSetName, int timeout_secs, boolean mode)
  {
    if ((targetClassName != null) && (testSetName != null)) {
      try {
        TestCaseExecuter test_engine = new TestCaseExecuter(targetClassName);
        test_engine.setTimeOut(timeout_secs);

        test_engine.readTestSet(testSetName);

        TestResult test_result = new TestResult();
        if (mode)
          test_result = test_engine.runClassMutants();
        else {
          test_result = test_engine.runTraditionalMutants("All method");
        }
        return test_result;
      }
      catch (Exception e) {
        System.err.println(e);
        return null;
      }
    }
    System.out.println(" [Error] Please check test target or test suite ");
    return null;
  }

  private static void setMutationSystemPathFor(String file_name)
  {
    try
    {
      String temp = file_name.substring(0, file_name.length() - ".java".length());
      temp = temp.replace('/', '.');
      temp = temp.replace('\\', '.');
      int separator_index = temp.lastIndexOf(".");
      if (separator_index >= 0)
        MutationSystem.CLASS_NAME = temp.substring(separator_index + 1, temp.length());
      else {
        MutationSystem.CLASS_NAME = temp;
      }

      String mutant_dir_path = MutationSystem.MUTANT_HOME + "/" + temp;
      File mutant_path = new File(mutant_dir_path);
      mutant_path.mkdir();

      String class_mutant_dir_path = mutant_dir_path + "/" + MutationSystem.CM_DIR_NAME;
      File class_mutant_path = new File(class_mutant_dir_path);
      class_mutant_path.mkdir();

      String traditional_mutant_dir_path = mutant_dir_path + "/" + MutationSystem.TM_DIR_NAME;
      File traditional_mutant_path = new File(traditional_mutant_dir_path);
      traditional_mutant_path.mkdir();

      String original_dir_path = mutant_dir_path + "/" + MutationSystem.ORIGINAL_DIR_NAME;
      File original_path = new File(original_dir_path);
      original_path.mkdir();

      MutationSystem.CLASS_MUTANT_PATH = class_mutant_dir_path;
      MutationSystem.TRADITIONAL_MUTANT_PATH = traditional_mutant_dir_path;
      MutationSystem.ORIGINAL_PATH = original_dir_path;
      MutationSystem.DIR_NAME = temp;
    }
    catch (Exception e) {
      System.err.println(e);
    }
  }
}