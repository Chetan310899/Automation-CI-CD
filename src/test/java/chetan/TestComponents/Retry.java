package chetan.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		int count= 0;
		int MaxTry=1;
		
		if (count <MaxTry) {
			count++;
			return true;
		}
		return false;
	}

}