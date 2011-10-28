package br.jabuti.probe.mobiledevice.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

final class Header 
{
	int dataType;
	String className;
	boolean compressedStream;

	// CLASS: mucode.Header:
	Header(int dType, String cName, boolean compressionON)
	{
		super();
		this.dataType = -1;
		this.className = null;
		this.compressedStream = false;
		this.dataType = dType;
		this.className = cName;
		this.compressedStream = compressionON;
		return;

	}

	Header(Socket s) throws IOException
	{
		super();
		DataInputStream in = null;

		this.dataType = -1;
		this.className = null;
		this.compressedStream = false;
		DataInputStream JdecGenerated21 = new DataInputStream(s.getInputStream());
		in = JdecGenerated21;
		this.dataType = in.read();
		this.className = in.readUTF();
		this.compressedStream = in.readBoolean();
		return;

	}

	void ship(Socket socket) throws IOException
	{
		DataOutputStream os = null;

		DataOutputStream JdecGenerated2 = new DataOutputStream(socket.getOutputStream());
		os = JdecGenerated2;
		os.write(this.dataType);
		os.writeUTF(this.className);
		os.writeBoolean(this.compressedStream);
		return;

	}
}
