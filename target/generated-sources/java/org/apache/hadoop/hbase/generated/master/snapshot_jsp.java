package org.apache.hadoop.hbase.generated.master;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import java.util.HashMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.master.HMaster;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.FSUtils;
import org.apache.hadoop.hbase.protobuf.ProtobufUtil;
import org.apache.hadoop.hbase.protobuf.generated.HBaseProtos.SnapshotDescription;
import org.apache.hadoop.hbase.snapshot.SnapshotInfo;
import org.apache.hadoop.util.StringUtils;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hbase.HConstants;

public final class snapshot_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');

  HMaster master = (HMaster)getServletContext().getAttribute(HMaster.MASTER);
  Configuration conf = master.getConfiguration();
  HBaseAdmin hbadmin = new HBaseAdmin(conf);
  boolean readOnly = conf.getBoolean("hbase.master.ui.readonly", false);
  String snapshotName = request.getParameter("name");
  SnapshotDescription snapshot = null;
  SnapshotInfo.SnapshotStats stats = null;
  for (SnapshotDescription snapshotDesc: hbadmin.listSnapshots()) {
    if (snapshotName.equals(snapshotDesc.getName())) {
      snapshot = snapshotDesc;
      stats = SnapshotInfo.getSnapshotStats(conf, snapshot);
      break;
    }
  }

  String action = request.getParameter("action");
  String cloneName = request.getParameter("cloneName");
  boolean isActionResultPage = (!readOnly && action != null);

      out.write("\n\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!-- Commenting out DOCTYPE so our blue outline shows on hadoop 0.20.205.0, etc.\n     See tail of HBASE-2110 for explaination.\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n-->\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/hbase.css\" />\n");
 if (isActionResultPage) { 
      out.write("\n  <title>HBase Master: ");
      out.print( master.getServerName() );
      out.write("</title>\n  <meta http-equiv=\"refresh\" content=\"5,javascript:history.back()\" />\n");
 } else { 
      out.write("\n  <title>Snapshot: ");
      out.print( snapshotName );
      out.write("</title>\n");
 } 
      out.write("\n</head>\n<body>\n<a id=\"logo\" href=\"http://wiki.apache.org/lucene-hadoop/Hbase\"><img src=\"/static/hbase_logo.png\" alt=\"HBase Logo\" title=\"HBase Logo\" /></a>\n");
 if (isActionResultPage) { 
      out.write("\n  <h1>Snapshot action request...</h1>\n");

  if (action.equals("restore")) {
    hbadmin.restoreSnapshot(snapshotName);
    
      out.write(" Restore Snapshot request accepted. ");

  } else if (action.equals("clone")) {
    if (cloneName != null && cloneName.length() > 0) {
      hbadmin.cloneSnapshot(snapshotName, cloneName);
      
      out.write(" Clone from Snapshot request accepted. ");

    } else {
      
      out.write(" Clone from Snapshot request failed, No table name specified. ");

    }
  }

      out.write("\n  <p>Go <a href=\"javascript:history.back()\">Back</a>, or wait for the redirect.\n</div>\n");
 } else if (snapshot == null) { 
      out.write("\n  <h1>Snapshot \"");
      out.print( snapshotName );
      out.write("\" does not exists</h1>\n  <p id=\"links_menu\"><a href=\"/master.jsp\">Master</a>, <a href=\"/logs/\">Local logs</a>, <a href=\"/stacks\">Thread Dump</a>, <a href=\"/logLevel\">Log Level</a></p>\n<hr id=\"head_rule\" />\n  <p>Go <a href=\"javascript:history.back()\">Back</a>, or wait for the redirect.\n");
 } else { 
      out.write("\n  <h1>Snapshot: ");
      out.print( snapshotName );
      out.write("</h1>\n  <p id=\"links_menu\"><a href=\"/master.jsp\">Master</a>, <a href=\"/logs/\">Local logs</a>, <a href=\"/stacks\">Thread Dump</a>, <a href=\"/logLevel\">Log Level</a></p>\n  <hr id=\"head_rule\" />\n  <h2>Snapshot Attributes</h2>\n  <table class=\"table\" width=\"90%\" >\n    <tr>\n        <th>Table</th>\n        <th>Creation Time</th>\n        <th>Type</th>\n        <th>Format Version</th>\n        <th>State</th>\n    </tr>\n    <tr>\n        <td><a href=\"table.jsp?name=");
      out.print( snapshot.getTable() );
      out.write('"');
      out.write('>');
      out.print( snapshot.getTable() );
      out.write("</a></td>\n        <td>");
      out.print( new Date(snapshot.getCreationTime()) );
      out.write("</td>\n        <td>");
      out.print( snapshot.getType() );
      out.write("</td>\n        <td>");
      out.print( snapshot.getVersion() );
      out.write("</td>\n        ");
 if (stats.isSnapshotCorrupted()) { 
      out.write("\n          <td style=\"font-weight: bold; color: #dd0000;\">CORRUPTED</td>\n        ");
 } else { 
      out.write("\n          <td>ok</td>\n        ");
 } 
      out.write("\n    </tr>\n  </table>\n  <p>\n    ");
      out.print( stats.getStoreFilesCount() );
      out.write(" HFiles (");
      out.print( stats.getArchivedStoreFilesCount() );
      out.write(" in archive),\n    total size ");
      out.print( StringUtils.humanReadableInt(stats.getStoreFilesSize()) );
      out.write("\n    (");
      out.print( stats.getSharedStoreFilePercentage() );
      out.write("&#37;\n    ");
      out.print( StringUtils.humanReadableInt(stats.getSharedStoreFilesSize()) );
      out.write(" shared with the source\n    table)\n  </p>\n  <p>\n    ");
      out.print( stats.getLogsCount() );
      out.write(" Logs, total size\n    ");
      out.print( StringUtils.humanReadableInt(stats.getLogsSize()) );
      out.write("\n  </p>\n  ");
 if (stats.isSnapshotCorrupted()) { 
      out.write("\n    <h3>CORRUPTED Snapshot</h3>\n    <p>\n      ");
      out.print( stats.getMissingStoreFilesCount() );
      out.write(" hfile(s) and\n      ");
      out.print( stats.getMissingLogsCount() );
      out.write(" log(s) missing.\n    <p>\n  ");
 } 
      out.write('\n');

  } // end else

HConnectionManager.deleteConnection(hbadmin.getConfiguration());

      out.write("\n\n\n");
 if (!readOnly && action == null && snapshot != null) { 
      out.write("\n<p><hr><p>\nActions:\n<p>\n<center>\n<table style=\"border-style: none\" width=\"90%\">\n<tr>\n  <form method=\"get\">\n  <input type=\"hidden\" name=\"action\" value=\"clone\">\n  <input type=\"hidden\" name=\"name\" value=\"");
      out.print( snapshotName );
      out.write("\">\n  <td style=\"border-style: none; text-align: center\">\n      <input style=\"font-size: 12pt; width: 10em\" type=\"submit\" value=\"Clone\" class=\"btn\"></td>\n  <td style=\"border-style: none\" width=\"5%\">&nbsp;</td>\n  <td style=\"border-style: none\">New Table Name (clone):<input type=\"text\" name=\"cloneName\" size=\"40\"></td>\n  <td style=\"border-style: none\">\n    This action will create a new table by cloning the snapshot content.\n    There are no copies of data involved.\n    And writing on the newly created table will not influence the snapshot data.\n  </td>\n  </form>\n</tr>\n<tr><td style=\"border-style: none\" colspan=\"4\">&nbsp;</td></tr>\n<tr>\n  <form method=\"get\">\n  <input type=\"hidden\" name=\"action\" value=\"restore\">\n  <input type=\"hidden\" name=\"name\" value=\"");
      out.print( snapshotName );
      out.write("\">\n  <td style=\"border-style: none; text-align: center\">\n      <input style=\"font-size: 12pt; width: 10em\" type=\"submit\" value=\"Restore\" class=\"btn\"></td>\n  <td style=\"border-style: none\" width=\"5%\">&nbsp;</td>\n  <td style=\"border-style: none\">&nbsp;</td>\n  <td style=\"border-style: none\">Restore a specified snapshot.\n  The restore will replace the content of the original table,\n  bringing back the content to the snapshot state.\n  The table must be disabled.</td>\n  </form>\n</tr>\n</table>\n</center>\n<p>\n</div>\n");
 } 
      out.write("\n</body>\n</html>\n");
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
