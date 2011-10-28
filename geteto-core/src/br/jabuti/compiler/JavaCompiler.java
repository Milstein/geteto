package br.jabuti.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ironiacorp.errorhandler.Error;
import com.ironiacorp.errorhandler.ErrorMessage;
import com.ironiacorp.errorhandler.PlainTextErrorLocation;
import com.ironiacorp.errorhandler.PlainTextRange;

public class JavaCompiler implements Compiler
{
	private static final Logger log = LoggerFactory.getLogger(JavaCompiler.class);
	
	@SuppressWarnings("restriction")
	public void compile(File... files)
	{
		javax.tools.JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(files);
		CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
		Boolean result = task.call();

		List<Diagnostic<? extends JavaFileObject>> diags = diagnostics.getDiagnostics();
		Iterator<Diagnostic<? extends JavaFileObject>> i = diags.iterator();
		
		Error error = new Error(this);
		while (i.hasNext()) {
			Diagnostic<? extends JavaFileObject> diagnostic = i.next();
			PlainTextRange range = new PlainTextRange();
			PlainTextErrorLocation location = new PlainTextErrorLocation();
			ErrorMessage message = new ErrorMessage();
			
			range.setColumnStart((int) diagnostic.getColumnNumber());
			range.setColumnEnd((int) diagnostic.getColumnNumber());
			range.setLineStart((int) diagnostic.getStartPosition());
			range.setLineEnd((int) diagnostic.getEndPosition());
			location.setFile(new File(diagnostic.getSource().toString()));
			location.addRange(range);
			message.addLocation(location);
			message.setDescription(diagnostic.getMessage(null));
			error.setMessage(message);
			switch (diagnostic.getKind()) {
				case ERROR:
					log.error(error.toString());
					break;
				case WARNING:
				case MANDATORY_WARNING:
				case NOTE:
				case OTHER:
					log.warn(error.toString());
			}
		}

		if (result == false) {
			throw new UnsupportedOperationException(error);
		}

		
		try {
			fileManager.close();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
