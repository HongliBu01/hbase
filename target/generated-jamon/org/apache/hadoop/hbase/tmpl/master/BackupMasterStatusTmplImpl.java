// Autogenerated Jamon implementation
// /proj/ucare/honglibu/hbase-0.94.12/src/main/jamon/org/apache/hadoop/hbase/tmpl/master/BackupMasterStatusTmpl.jamon

package org.apache.hadoop.hbase.tmpl.master;

// 23, 1
import java.util.*;
// 24, 1
import org.apache.hadoop.hbase.util.Bytes;
// 25, 1
import org.apache.hadoop.hbase.ServerName;
// 26, 1
import org.apache.hadoop.hbase.ClusterStatus;
// 27, 1
import org.apache.hadoop.hbase.master.HMaster;
// 28, 1
import org.apache.hadoop.hbase.master.ServerManager;
// 29, 1
import org.apache.hadoop.hbase.master.AssignmentManager;
// 30, 1
import org.apache.hadoop.hbase.master.ActiveMasterManager;

public class BackupMasterStatusTmplImpl
  extends org.jamon.AbstractTemplateImpl
  implements org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl.Intf

{
  private final HMaster master;
  protected static org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl.ImplData __jamon_setOptionalArguments(org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl.ImplData p_implData)
  {
    return p_implData;
  }
  public BackupMasterStatusTmplImpl(org.jamon.TemplateManager p_templateManager, org.apache.hadoop.hbase.tmpl.master.BackupMasterStatusTmpl.ImplData p_implData)
  {
    super(p_templateManager, __jamon_setOptionalArguments(p_implData));
    master = p_implData.getMaster();
  }
  
  public void renderNoFlush(@SuppressWarnings({"unused","hiding"}) final java.io.Writer jamonWriter)
    throws java.io.IOException
  {
    // 32, 1
    
Collection<ServerName> masters;

if (master.isActiveMaster()) {
    ClusterStatus status = master.getClusterStatus();
    masters = status.getBackupMasters();
} else{
    ServerName sn = master.getActiveMasterManager().getActiveMaster() ;
    assert sn != null : "Failed to retreive master's ServerName!";

    List<ServerName> serverNames = new ArrayList<ServerName>(1);
    serverNames.add(sn);
    masters = Collections.unmodifiableCollection(serverNames);
}

    // 48, 1
    
ServerName [] serverNames = masters.toArray(new ServerName[masters.size()]);

    // 51, 1
    if ((!master.isActiveMaster()) )
    {
      // 51, 35
      jamonWriter.write("\n    <h2>Master</h2>\n    <a href=\"http://");
      // 53, 21
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverNames[0].getHostname()), jamonWriter);
      // 53, 55
      jamonWriter.write(":");
      // 53, 56
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getConfiguration().getInt("hbase.master.info.port", 60010)), jamonWriter);
      // 53, 127
      jamonWriter.write("/master-status\" target=\"_blank\">");
      // 53, 159
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverNames[0].getHostname()), jamonWriter);
      // 53, 193
      jamonWriter.write("</a>\n");
    }
    // 54, 1
    else
    {
      // 54, 8
      jamonWriter.write("\n    <h2>Backup Masters</h2>\n\n    <table class=\"table table-striped\">\n    <tr>\n        <th>ServerName</th>\n        <th>Port</th>\n        <th>Start Time</th>\n    </tr>\n    ");
      // 63, 5
      
    Arrays.sort(serverNames);
    for (ServerName serverName: serverNames) {
    
      // 67, 5
      jamonWriter.write("<tr>\n        <td><a href=\"http://");
      // 68, 29
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverName.getHostname()), jamonWriter);
      // 68, 59
      jamonWriter.write(":");
      // 68, 60
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(master.getConfiguration().getInt("hbase.master.info.port", 60010)), jamonWriter);
      // 68, 131
      jamonWriter.write("/master-status\" target=\"_blank\">");
      // 68, 163
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverName.getHostname()), jamonWriter);
      // 68, 193
      jamonWriter.write("</a></td>\n        <td>");
      // 69, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(serverName.getPort()), jamonWriter);
      // 69, 39
      jamonWriter.write("</td>\n        <td>");
      // 70, 13
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf(new Date(serverName.getStartcode())), jamonWriter);
      // 70, 54
      jamonWriter.write("</td>\n    </tr>\n    ");
      // 72, 5
      
    }
    
      // 75, 5
      jamonWriter.write("<tr><td>Total:");
      // 75, 19
      org.jamon.escaping.Escaping.HTML.write(org.jamon.emit.StandardEmitter.valueOf((masters != null) ? masters.size() : 0), jamonWriter);
      // 75, 63
      jamonWriter.write("</td>\n    </table>\n");
    }
    // 77, 7
    jamonWriter.write("\n");
  }
  
  
}
