package unitconversiontool;

public interface Convertor {
 static Double convertkmintomiles(float km) {
	return  km * 0.6213;
 }
 static Double convertkgintolbs(float kg) {
		return  kg * 2.204;
	 }
}
