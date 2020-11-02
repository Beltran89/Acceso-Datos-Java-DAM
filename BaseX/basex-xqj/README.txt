+------------------------------------------------------------+
| BaseX XQJ | http://xqj.net/basex                           |
+------------------------------------------------------------+

Version 1.4.0:
 * Aligned with BaseX 8.0 (Massive thanks to Christian Gruen for his help)

Version 1.3.0:
 * Added a BaseX Local/Embedded XQDataSource implementation.
 * Various bug fixes.

Version 1.2.3:
 * Fixed: 0000023, fixed Saxon compatibility issues.

Version 1.2.2:
 * Fixed: 0000021, work around implemented for certain dodgy versions of Java.

Version 1.2.1:
 * Fixed: 0000022, Unrecognised error message format provided by server.

Version 1.2.0:
 * Supports binding to XQuery 3.0 external variables with default values.
 * Binding the context item in XQuery 3.0 makes use of context item declaration.

Version 1.1.0:
 * Supports XQuery 3.0 + XQuery Update Facility + XQuery Full Text extensions.
 * Various bug fixes.

Version 1.0.0:
 * Initial Beta Release
 * Supports XQuery "1.0", passes 99.6% of XQJ Test Compatibility Kit.

Known Limitations
-----------------
 * Does not support stating that a query is read only
 * Does not support XQuery 3.0 data types or BaseX proprietary basex:binary

Package Structure
-----------------

/lib/basex-xqj-x.x.x.jar
  BaseX XQJ implementation binary
    * Depends on the xqj-api-1.0.jar
    * Depends on xqj2-x.x.x.jar

/lib/xqj-api-1.0.jar
  XQJ (JSR 225) interfaces

/lib/xqj2-x.x.x.jar
  XQJ2 interfaces, check out http://xqj2.com
    * Depends on xqj-api-1.0.jar

/lib/basex-xqj-examples-x.x.x.jar
  Compiled binaries of the BaseX XQJ example applications.

/src/**
  Source files of all BaseX XQJ example applications

/doc/documentation.pdf
  The BaseX XQJ documentation / manual

/doc/javadoc/**
  JSR 225: XQuery API for Java API Documentation

/LICENCE.txt
  The licence for using this software.

Copyright (c) 2015. xqj.net. All rights reserved.
