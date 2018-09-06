package HBaseTest.HBaseTest1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.protobuf.generated.ClientProtos.Column;

public class Test1 {
	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		Configuration config = HBaseConfiguration.create();
		config.addResource("core-site.xml");
		config.addResource("hbase-site.xml");
		
		//获得配置选项的值
		String fs1 = config.get("fs.defaultFS");
		//设置配置选项,一个是文件系统，一个是zookeeper，一个是端口
		config.set("fs.defaultFS", "hdfs://60.24.64.141");
		config.set("hbase.zookeeper.quorum", "60.24.64.141");
		config.set("hbase.zookeeper.property.clientPort", "2181");
//		System.out.println(fs1);
		//要创建表，则要有HBaseAdmin，提供了一个接口来管理Transwarp HBase数据库的表信息。包括：创建表,删除表,列出表 项,使表有效或无
		//效,以及添加或删除表列族成员等。
		HBaseAdmin admin = new HBaseAdmin(config);
//		boolean e = admin.tableExists("r_blaze_core_info".getBytes());
//		System.out.println(e);
		//创建表的第一步要有表的描述信息。描述一个Transwarp HBase表的所有细节,包括列族等信息。在这里可以添加列族
		HTableDescriptor desc = new HTableDescriptor("lishuai_test");
		//在创建表的描述的时候要有列的描述，这里可以增加列族
		HColumnDescriptor family = new HColumnDescriptor("baseInfo");
		
//		desc.addFamily(family);
//		admin.createTable(desc);
		
		//向表中添加数据,要使用HTable
		HTable table = new HTable(config, "lishuai_test");
		//创建要插入的数据,首先创建put对象，通过行名为参数来创建
		Put put = new Put("row1".getBytes());
		put.add("baseInfo".getBytes(),"baseInfo".getBytes(), "123321".getBytes());
		table.put(put);
		
		
		
		//添加列族
		HColumnDescriptor column = new HColumnDescriptor("11111111111111");
		admin.addColumn("lishuai_test", column);
		System.out.println("over");
	}
}
