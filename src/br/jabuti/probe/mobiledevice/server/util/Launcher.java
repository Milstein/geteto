package br.jabuti.probe.mobiledevice.server.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.Vector;

import br.jabuti.probe.mobiledevice.server.MuServer;

public class Launcher
{
	private MuServer server;

	public Launcher()
	{
		super();
		server = new MuServer();
	}

	public Launcher(MuServer server)
	{
		super();
		this.server = server;
	}

	public MuServer getServer()
	{
		return this.server;
	}

	public void launch(String[] args, int index)
	{
		try {
			this.parseArgs(args, index);
			this.server.boot();
			this.server.properties.list(System.out);

		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
			if (this.server.isMessagesOn() != false) {
				printHelp();

			}
			System.exit(0);
		}
	}

	public void parseArgs(String[] args, int index)
	{
		String propvalue = null;
		String[] classnames = null;
		String[] packagenames = null;

		int i = 0;
		int j = 0;
		if ((args.length != 0 && index < 0 || (index > args.length || args == null)))

		{
			IllegalArgumentException JdecGenerated21 = new IllegalArgumentException();
			throw JdecGenerated21;

		}
		if (args[0].equals("-propertyfile") != false) {
			if (args.length != 2) {
				printHelp();
				System.exit(0);

			} else {
				try {
					this.server.loadProperties(args[1]);

				} catch (FileNotFoundException fe) {
					StringBuffer JdecGenerated73 = new StringBuffer("Property file ");
					System.out.println(JdecGenerated73.append(args[1]).append(" not found. Halting...")
							.toString());
					System.exit(0);

				} catch (IOException e) {
					System.out.println("I/O problems. Halting...");
					e.printStackTrace();
					System.exit(0);
					return;

				}

			}
		} else {
			i = index;
			while (true)

			{

				if (++i >= args.length) {
					break;

				}

				if (!args[i].equals("-port")) {
					if (args[i].equals("-timeout") != false) {
						this.server.setProperty(args[i].substring(1), args[++i]);

					}

				} else {
					if (!args[i].equals("-compression")) {
						if (!args[i].equals("-debug")) {
							if (!args[i].equals("-messages")) {
								if (args[i].equals("-errors") != false) {

								}

							}

						}
						this.server.setProperty(args[i].substring(1), this.parseFlag(args[++i]));

					} else {

					}
					if (args[i].equals("-ubiclasses") != false) {
						classnames = this.parseMultipleArgs(args, ++i);
						i = (i + classnames.length);
						propvalue = "";
						j = 0;
						while (true)

						{

							if (++j >= classnames.length) {
								break;

							}

							this.server.addUbiquitousClass(classnames[j]);
							StringBuffer JdecGenerated297 = new StringBuffer(String.valueOf(propvalue));
							propvalue = JdecGenerated297.append(classnames[j]).append(" ").toString();

						}

					}

				}
				this.server.setProperty("ubiclasses", propvalue);

			}
			if (args[i].equals("-ubipackages") != false) {
				packagenames = this.parseMultipleArgs(args, i++);
				i = (i + packagenames.length);
				propvalue = "";
				j = 0;
				while (true)

				{

					if (++j >= packagenames.length) {
						break;

					}

					this.server.addUbiquitousPackage(packagenames[j]);
					StringBuffer JdecGenerated402 = new StringBuffer(String.valueOf(propvalue));
					propvalue = JdecGenerated402.append(packagenames[j]).append(" ").toString();

				}
				this.server.setProperty("ubipackages", propvalue);

			} else {
				IllegalArgumentException JdecGenerated457 = new IllegalArgumentException(args[i]);
				throw JdecGenerated457;

			}

			return;
		}

	}

	// CLASS: mucode.util.Launcher:
	protected String[] parseMultipleArgs(String[] args, int start)
	{
		String[] res = null;
		Vector vr = new Vector();
		int i = 0;
		i = start;
		while (true) {
			vr.addElement(args[i]);
			if (++i >= args.length || this.isFlag(args[i]) != false) {
				break;
			}
			String[] JdecGeneratedVar48 = new String[vr.size()];
			res = JdecGeneratedVar48;
			vr.copyInto(res);
		}

		return res;
	}

	// CLASS: mucode.util.Launcher:
	private boolean isFlag(String arg)
	{
		return arg.startsWith("-");

	}

	// CLASS: mucode.util.Launcher:
	protected String parseFlag(String flag)
	{
		if (flag.equals("on") != false) {
			return "true";

		}
		if (flag.equals("off") != false) {
			return "false";

		}
		IllegalArgumentException JdecGenerated26 = new IllegalArgumentException(flag);
		throw JdecGenerated26;

	}

	// CLASS: mucode.util.Launcher:
	private static void printHelp()
	{
		System.out.println("Usage: java mucode.util.Launcher ");
		System.out.println("                            [-port <number>]");
		System.out.println("                            [-timeout <millisec>]");
		System.out.println("                            [-compression {on|off}]");
		System.out.println("                            [-debug {on|off}]");
		System.out.println("                            [-messages {on|off}]");
		System.out.println("                            [-errors {on|off}]");
		System.out.println("                            [-ubiclasses <classname1 classname2 ...>]");
		System.out.println("                            [-ubipackages <packagename1 packagename2 ...>]");
		System.out.println("                            [-propertyfile <filename>]");
		System.out.println("The -propertyfile option is mutually exclusive with all the other options.");
		return;

	}

	// CLASS: mucode.util.Launcher:
	public static void main(String[] args)
	{

		Launcher JdecGenerated2 = new Launcher();
		JdecGenerated2.launch(args, 0);
		return;

	}

}
