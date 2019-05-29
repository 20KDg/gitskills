package MR.master;


import MR.mapper.DataMapper;
import MR.reducer.DataReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class DataMaster {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Path inputPath = new Path("/input/51Job_python_5000.csv");
        Path outputPath = new Path("/output/51job/fbzd");

        //初始化配置
        Configuration conf= new Configuration();
        FileSystem fs = FileSystem.get(conf);

        //初始化job参数,指定job名称
        Job job = Job.getInstance(conf,"datacount");
        //设置运行job的类
        job.setJarByClass(DataMaster.class);
        //设置Mapper类
        job.setMapperClass(DataMapper.class);
        //设置Reducer类
        job.setReducerClass(DataReducer.class);
        //设置Map的输出数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置Reducer的输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入的路径
        FileInputFormat.setInputPaths(job,inputPath);

        //设置输出的路径
        FileOutputFormat.setOutputPath(job,outputPath);

        if(fs.exists(outputPath)){
            fs.delete(outputPath,true);
        }

        //提交job
        boolean result = job.waitForCompletion(true);
        if (result){
            System.out.println("执行成功");
        }
    }


}
