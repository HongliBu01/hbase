package org.apache.hadoop.hbase.generated.master;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.HashMap;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.HServerAddress;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.HServerInfo;
import org.apache.hadoop.hbase.HServerLoad;
import org.apache.hadoop.hbase.HServerLoad.RegionLoad;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.master.HMaster;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.FSUtils;
import java.util.Map;
import org.apache.hadoop.hbase.HConstants;

public final class table_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  String tableName = request.getParameter("name");
  HTable table = new HTable(conf, tableName);
  String tableHeader = "<h2>Table Regions</h2><table><tr><th>Name</th><th>Region Server</th><th>Start Key</th><th>End Key</th><th>Requests</th></tr>";
  ServerName rl = master.getCatalogTracker().getRootLocation();
  boolean showFragmentation = conf.getBoolean("hbase.master.ui.fragmentation.enabled", false);
  boolean readOnly = conf.getBoolean("hbase.master.ui.readonly", false);
  Map<String, Integer> frags = null;
  if (showFragmentation) {
      frags = FSUtils.getTableFragmentation(master);
  }
  // HARDCODED FOR NOW TODO: FIX GET FROM ZK
  // This port might be wrong if RS actually ended up using something else.
  int infoPort = conf.getInt("hbase.regionserver.info.port", 60030);

      out.write("\n\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!-- Commenting out DOCTYPE so our blue outline shows on hadoop 0.20.205.0, etc.\n     See tail of HBASE-2110 for explaination.\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \n  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> \n-->\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n\n");

  String action = request.getParameter("action");
  String key = request.getParameter("key");
  if ( !readOnly && action != null ) {

      out.write("\n<head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/hbase.css\" />\n<meta http-equiv=\"refresh\" content=\"5,javascript:history.back()\" />\n</head>\n<body>\n<a id=\"logo\" href=\"http://wiki.apache.org/lucene-hadoop/Hbase\"><img src=\"/static/hbase_logo.png\" alt=\"HBase Logo\" title=\"HBase Logo\" /></a>\n<h1 id=\"page_title\">Table action request accepted</h1>\n<p><hr><p>\n");

  if (action.equals("split")) {
    if (key != null && key.length() > 0) {
      hbadmin.split(key);
    } else {
      hbadmin.split(tableName);
    }
    
    
      out.write(" Split request accepted. ");

  } else if (action.equals("compact")) {
    if (key != null && key.length() > 0) {
      hbadmin.compact(key);
    } else {
      hbadmin.compact(tableName);
    }
    
      out.write(" Compact request accepted. ");

  }

      out.write("\n<p>Go <a href=\"javascript:history.back()\">Back</a>, or wait for the redirect.\n\n</body>\n");

} else {

      out.write("\n<head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>\n<title>Table: ");
      out.print( tableName );
      out.write("</title>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/hbase.css\" />\n</head>\n<body>\n<a id=\"logo\" href=\"http://wiki.apache.org/lucene-hadoop/Hbase\"><img src=\"/static/hbase_logo.png\" alt=\"HBase Logo\" title=\"HBase Logo\" /></a>\n<h1 id=\"page_title\">Table: ");
      out.print( tableName );
      out.write("</h1>\n<p id=\"links_menu\"><a href=\"/master.jsp\">Master</a>, <a href=\"/logs/\">Local logs</a>, <a href=\"/stacks\">Thread Dump</a>, <a href=\"/logLevel\">Log Level</a></p>\n<hr id=\"head_rule\" />\n");

  if(tableName.equals(Bytes.toString(HConstants.ROOT_TABLE_NAME))) {

      out.write('\n');
      out.print( tableHeader );
      out.write('\n');

  String url = "http://" + rl.getHostname() + ":" + infoPort + "/";

      out.write("\n<tr>\n  <td>");
      out.print( tableName );
      out.write("</td>\n  <td><a href=\"");
      out.print( url );
      out.write('"');
      out.write('>');
      out.print( rl.getHostname() );
      out.write(':');
      out.print( rl.getPort() );
      out.write("</a></td>\n  <td>-</td>\n  <td></td>\n  <td>-</td>\n</tr>\n</table>\n");

  } else if(tableName.equals(Bytes.toString(HConstants.META_TABLE_NAME))) {

      out.write('\n');
      out.print( tableHeader );
      out.write('\n');

  // NOTE: Presumes one meta region only.
  HRegionInfo meta = HRegionInfo.FIRST_META_REGIONINFO;
  ServerName metaLocation = master.getCatalogTracker().waitForMeta(1);
  for (int i = 0; i < 1; i++) {
    String url = "http://" + metaLocation.getHostname() + ":" + infoPort + "/";

      out.write("\n<tr>\n  <td>");
      out.print( meta.getRegionNameAsString() );
      out.write("</td>\n    <td><a href=\"");
      out.print( url );
      out.write('"');
      out.write('>');
      out.print( metaLocation.getHostname().toString() + ":" + infoPort );
      out.write("</a></td>\n    <td>-</td><td>");
      out.print( Bytes.toString(meta.getStartKey()) );
      out.write("</td><td>");
      out.print( Bytes.toString(meta.getEndKey()) );
      out.write("</td>\n</tr>\n");
  } 
      out.write("\n</table>\n");
} else {
  try { 
      out.write("\n<h2>Table Attributes</h2>\n<table>\n  <tr>\n      <th>Attribute Name</th>\n      <th>Value</th>\n      <th>Description</th></tr>\n  <tr>\n      <td>Enabled</td>\n      <td>");
      out.print( hbadmin.isTableEnabled(table.getTableName()) );
      out.write("</td>\n      <td>Is the table enabled</td>\n  </tr>\n  <tr>\n      <td>Compaction</td>\n      <td>");
      out.print( hbadmin.getCompactionState(table.getTableName()) );
      out.write("</td>\n      <td>Is the table compacting</td>\n  </tr>\n");
  if (showFragmentation) { 
      out.write("\n  <tr>\n      <td>Fragmentation</td>\n      <td>");
      out.print( frags.get(tableName) != null ? frags.get(tableName).intValue() + "%" : "n/a" );
      out.write("</td>\n      <td>How fragmented is the table. After a major compaction it is 0%.</td>\n  </tr>\n");
  } 
      out.write("\n</table>\n");

  Map<String, Integer> regDistribution = new HashMap<String, Integer>();
  Map<HRegionInfo, ServerName> regions = table.getRegionLocations();
  if(regions != null && regions.size() > 0) { 
      out.write('\n');
      out.print(     tableHeader );
      out.write('\n');

  for (Map.Entry<HRegionInfo, ServerName> hriEntry : regions.entrySet()) {
    HRegionInfo regionInfo = hriEntry.getKey();
    ServerName addr = hriEntry.getValue();
    long req = 0;

    String urlRegionServer = null;

    if (addr != null) {
      HServerLoad sl = master.getServerManager().getLoad(addr);
      if (sl != null) {
        Map<byte[], RegionLoad> map = sl.getRegionsLoad();
        if (map.containsKey(regionInfo.getRegionName())) {
          req = map.get(regionInfo.getRegionName()).getRequestsCount();
        }
        // This port might be wrong if RS actually ended up using something else.
        urlRegionServer =
            "http://" + addr.getHostname().toString() + ":" + infoPort + "/";
        Integer i = regDistribution.get(urlRegionServer);
        if (null == i) i = new Integer(0);
        regDistribution.put(urlRegionServer, i+1);
      }
    }

      out.write("\n<tr>\n  <td>");
      out.print( Bytes.toStringBinary(regionInfo.getRegionName()));
      out.write("</td>\n  ");

  if (urlRegionServer != null) {
  
      out.write("\n  <td>\n    <a href=\"");
      out.print( urlRegionServer );
      out.write('"');
      out.write('>');
      out.print( addr.getHostname().toString() + ":" + infoPort );
      out.write("</a>\n  </td>\n  ");

  } else {
  
      out.write("\n  <td class=\"undeployed-region\">not deployed</td>\n  ");

  }
  
      out.write("\n  <td>");
      out.print( Bytes.toStringBinary(regionInfo.getStartKey()));
      out.write("</td>\n  <td>");
      out.print( Bytes.toStringBinary(regionInfo.getEndKey()));
      out.write("</td>\n  <td>");
      out.print( req);
      out.write("</td>\n</tr>\n");
 } 
      out.write("\n</table>\n<h2>Regions by Region Server</h2>\n<table><tr><th>Region Server</th><th>Region Count</th></tr>\n");

  for (Map.Entry<String, Integer> rdEntry : regDistribution.entrySet()) {

      out.write("\n<tr>\n  <td>");
      out.print( rdEntry.getKey());
      out.write("</td>\n  <td>");
      out.print( rdEntry.getValue());
      out.write("</td>\n</tr>\n");
 } 
      out.write("\n</table>\n");
 }
} catch(Exception ex) {
  ex.printStackTrace(System.err);
}
} // end else

HConnectionManager.deleteConnection(hbadmin.getConfiguration());

      out.write("\n\n\n");
 if (!readOnly) { 
      out.write("\n<p><hr><p>\nActions:\n<p>\n<center>\n<table style=\"border-style: none\" width=\"90%\">\n<tr>\n  <form method=\"get\">\n  <input type=\"hidden\" name=\"action\" value=\"compact\">\n  <input type=\"hidden\" name=\"name\" value=\"");
      out.print( tableName );
      out.write("\">\n  <td style=\"border-style: none; text-align: center\">\n      <input style=\"font-size: 12pt; width: 10em\" type=\"submit\" value=\"Compact\"></td>\n  <td style=\"border-style: none\" width=\"5%\">&nbsp;</td>\n  <td style=\"border-style: none\">Region Key (optional):<input type=\"text\" name=\"key\" size=\"40\"></td>\n  <td style=\"border-style: none\">This action will force a compaction of all\n  regions of the table, or, if a key is supplied, only the region containing the\n  given key.</td>\n  </form>\n</tr>\n<tr><td style=\"border-style: none\" colspan=\"4\">&nbsp;</td></tr>\n<tr>\n  <form method=\"get\">\n  <input type=\"hidden\" name=\"action\" value=\"split\">\n  <input type=\"hidden\" name=\"name\" value=\"");
      out.print( tableName );
      out.write("\">\n  <td style=\"border-style: none; text-align: center\">\n      <input style=\"font-size: 12pt; width: 10em\" type=\"submit\" value=\"Split\"></td>\n  <td style=\"border-style: none\" width=\"5%\">&nbsp;</td>\n  <td style=\"border-style: none\">Region Key (optional):<input type=\"text\" name=\"key\" size=\"40\"></td>\n  <td style=\"border-style: none\">This action will force a split of all eligible\n  regions of the table, or, if a key is supplied, only the region containing the\n  given key. An eligible region is one that does not contain any references to\n  other regions. Split requests for noneligible regions will be ignored.</td>\n  </form>\n</tr>\n</table>\n</center>\n<p>\n");
 } 
      out.write('\n');

}

      out.write("\n\n</body>\n</html>\n");
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
