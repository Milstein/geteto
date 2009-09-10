package br.jabuti.probe.mobiledevice.server;

import java.io.PrintStream;
import java.io.PrintWriter;

public class MuCodeException extends Exception
{
	private Exception internal;
	private boolean internalFlag;

	public MuCodeException(Exception internal)
	{
		this();
		setInternal(internal);
		return;

	}

	public MuCodeException()
	{
		super();
		this.internal = null;
		this.internalFlag = false;
		return;

	}

	// CLASS: mucode.MuCodeException:
	public MuCodeException(String s)
	{
		super(s);
		this.internal = null;
		this.internalFlag = false;
		return;

	}

	public MuCodeException(Exception internal, String s)
	{
		super(s);
		this.internal = null;
		this.internalFlag = false;
		setInternal(internal);
		return;

	}

	public boolean hasInternal()
	{
		return this.internalFlag;

	}

	public Exception getInternal()
	{
		return this.internal;

	}

	public String toString()
	{
		String r = null;
		r = super.toString();
		if (this.internalFlag != false) {
			StringBuffer JdecGenerated14 = new StringBuffer(String.valueOf(r));
			r = JdecGenerated14.append(": ").append(this.internal.toString()).toString();

		}
		return r;

	}

	public void printStackTrace()
	{
		if (this.internalFlag != false) {
			System.err.println(super.toString());
			this.internal.printStackTrace();
			return;

		} else {
			super.printStackTrace();
			return;
		}

	}

	public void printStackTrace(PrintStream s)
	{
		if (this.internalFlag != false) {
			s.println(super.toString());
			this.internal.printStackTrace(s);
			return;

		} else {
			super.printStackTrace(s);
			return;
		}

	}

	// CLASS: mucode.MuCodeException:
	public void printStackTrace(PrintWriter s)
	{
		if (this.internalFlag != false) {
			s.println(super.toString());
			this.internal.printStackTrace(s);
			return;

		} else {
			super.printStackTrace(s);
			return;
		}

	}

	private void setInternal(Exception internal)
	{

		this.internal = internal;
		this.internalFlag = true;
		return;

	}

}
