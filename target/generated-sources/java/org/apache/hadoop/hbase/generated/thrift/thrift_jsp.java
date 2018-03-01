package org.apache.hadoop.hbase.generated.thrift;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.util.VersionInfo;
import java.util.Date;

public final class thrift_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.apache.jasper.runtime.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.apache.jasper.runtime.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n\n\n");

Configuration conf = (Configuration)getServletContext().getAttribute("hbase.conf");
long startcode = conf.getLong("startcode", System.currentTimeMillis());
String listenPort = conf.get("hbase.regionserver.thrift.port", "9090");
String serverInfo = listenPort + "," + String.valueOf(startcode);
String implType = conf.get("hbase.regionserver.thrift.server.type", "threadpool");
String compact = conf.get("hbase.regionserver.thrift.compact", "false");
String framed = conf.get("hbase.regionserver.thrift.framed", "false");

      out.write("\n\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!-- Commenting out DOCTYPE so our blue outline shows on hadoop 0.20.205.0, etc.\n     See tail of HBASE-2110 for explaination.\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n-->\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>\n<title>HBase Thrift Server</title>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/hbase.css\" />\n</head>\n\n<body>\n<a id=\"logo\" href=\"http://wiki.apache.org/lucene-hadoop/Hbase\"><img src=\"/static/hbase_logo.png\" alt=\"HBase Logo\" title=\"HBase Logo\" /></a>\n<h1 id=\"page_title\">ThriftServer: ");
      out.print( serverInfo );
      out.write("</h1>\n<p id=\"links_menu\">\n  <a href=\"/logs/\">Local logs</a>,\n  <a href=\"/stacks\">Thread Dump</a>,\n  <a href=\"/logLevel\">Log Level</a>,\n");
 if (HBaseConfiguration.isShowConfInServlet()) { 
      out.write("\n  <a href=\"/conf\">HBase Configuration</a>\n");
 } 
      out.write("\n</p>\n<hr id=\"head_rule\" />\n\n<h2>Attributes</h2>\n<table id=\"attributes_table\">\n<col style=\"width: 10%;\"/>\n<col />\n<col style=\"width: 20%;\"/>\n<tr><th>Attribute Name</th><th>Value</th><th>Description</th></tr>\n<tr><td>HBase Version</td><td>");
      out.print( VersionInfo.getVersion() );
      out.write(", r");
      out.print( VersionInfo.getRevision() );
      out.write("</td><td>HBase version and revision</td></tr>\n<tr><td>HBase Compiled</td><td>");
      out.print( VersionInfo.getDate() );
      out.write(',');
      out.write(' ');
      out.print( VersionInfo.getUser() );
      out.write("</td><td>When HBase version was compiled and by whom</td></tr>\n<tr><td>Thrift Server Start Time</td><td>");
      out.print( new Date(startcode) );
      out.write("</td><td>Date stamp of when this Thrift server was started</td></tr>\n<tr><td>Thrift Impl Type</td><td>");
      out.print( implType );
      out.write("</td><td>Thrift RPC engine implementation type chosen by this Thrift server</td></tr>\n<tr><td>Compact Protocol</td><td>");
      out.print( compact );
      out.write("</td><td>Thrift RPC engine uses compact protocol</td></tr>\n<tr><td>Framed Transport</td><td>");
      out.print( framed );
      out.write("</td><td>Thrift RPC engine uses framed transport</td></tr>\n</table>\n\n<hr id=\"foot_rule\" />\n<a href=\"http://wiki.apache.org/hadoop/Hbase/ThriftApi\">Apache HBase Wiki on Thrift</a>\n\n</body>\n</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
