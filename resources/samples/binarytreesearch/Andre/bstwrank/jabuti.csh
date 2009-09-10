#!/usr/bin/csh

setenv TOOL /mnt/aurimrv/jabuti

java -cp "${TOOL}:.:$TOOL/lib/BCEL.jar:$TOOL/lib/junit.jar:$TOOL/lib/jviewsall.jar:$TOOL/lib/dom.jar:$TOOL/lib/crimson.jar:$TOOL/lib/jaxp-api.jar" gui.JabutiGUI

