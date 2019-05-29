package MR.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//KEYIN,VALUEIN,KEYOUT,VALUEOUT:
//map阶段输入的Key,map阶段输入的Value
//map阶段输出的key,map阶段输出的value
public class DataMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String data = value.toString();
        if (isRightData(data)) {
            String[] temp = data.split(",");
            context.write(new Text(temp[1]), new IntWritable(1));
        }
    }
    boolean isRightData(String info){
        boolean flag = true;
        String[] items = info.split(",");
        for(String item: items){
            if (item.trim().isEmpty()){
                flag = false;
                break;
            }
        }
        return flag;
    }

}
