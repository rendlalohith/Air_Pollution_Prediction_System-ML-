//import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
//import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import java.util.Scanner;
public class TrainModel {
    static String lookIng(int n){
        if(n <= 50){
            return "Good";
        }
        else if(n <= 100){
            return "Moderate";
        }
        else if(n <= 150){
            return "Unhealthy for Sensitive Groups";
        }
        else if(n <= 200){
            return "Unhealthy";
        }
        else if(n <= 300){
            return "Very Unhealthy";
        }
        else{
            return "Hazardous";
        }
    }
    public static void main(String[] args) throws Exception {
        // Load dataset
        DataSource source = new DataSource("../data/weather.arff");
        Instances dataSet = source.getDataSet();
        dataSet.setClassIndex(dataSet.numAttributes()-1);
        //creating the model
        RandomForest Model = new RandomForest();
        Model.buildClassifier(dataSet);
        //System.out.println(Model);
        Scanner sc = new Scanner(System.in);
        DenseInstance newInstance = new DenseInstance(dataSet.numAttributes());
        newInstance.setDataset(dataSet);
       // Scanner sc = new Scanner(System.in);
        System.out.println("Enter the PM2.5:");
        double pm25 = sc.nextDouble();
        System.out.println("Enter the PM10:");
        double pm10 = sc.nextDouble();
        System.out.println("Enter the CO:");
        double co = sc.nextDouble();
        System.out.println("Enter the NO2:");
        double no2 = sc.nextDouble();
        System.out.println("Enter the SO2:");
        double so2 = sc.nextDouble();
        System.out.println("Enter the Temperature:");
        double temp = sc.nextDouble();
        System.out.println("Enter the Humidity:");
        double humidity = sc.nextDouble();
        newInstance.setValue(dataSet.attribute("pm25"), pm25);
        newInstance.setValue(dataSet.attribute("pm10"), pm10);
        newInstance.setValue(dataSet.attribute("co"), co);
        newInstance.setValue(dataSet.attribute("no2"), no2);
        newInstance.setValue(dataSet.attribute("so2"), so2);
        newInstance.setValue(dataSet.attribute("temp"), temp);
        newInstance.setValue(dataSet.attribute("humidity"), humidity);
        //train teh model by these data
        double prediction = Model.classifyInstance(newInstance);
        int aqi = (int) prediction;
        System.out.println("Predicted AQI : " + aqi);
        System.out.println("Category : " + lookIng(aqi));
    }
}