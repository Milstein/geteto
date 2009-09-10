package br.jabuti.probe.mobiledevice.server;

public interface MuConstants
{
	static final String PACKAGE_NAME = "mucode";
	static final int UNKNOWN = -1;
	static final String PORTkey = "port";
	static final String TIMEOUTkey = "timeout";
	static final String COMPRESSIONkey = "compression";
	static final String ERRORSkey = "errors";
	static final String MESSAGESkey = "messages";
	static final String DEBUGkey = "debug";
	static final String UBICLASSESkey = "ubiclasses";
	static final String UBIPACKAGESkey = "ubipackages";
	static final long TIMEOUT = 30000L;
	static final int SERVER_PORT = 1968;
	static final int GROUP = 1;
	static final int DYN_LINK = 2;
	static final int OK = 0;
	static final int REMOTE_ERROR = -1;
}
