package randomPackage;

import java.util.Arrays;

import org.testng.annotations.Test;

@Test()
public class RandomClass {
	
	@Test()
	public void m1() {
		
		int[] a = {1,2,3,4,5};
		int[] b = a;
		b[1]=10;
		System.out.println(Arrays.toString(a));
		
	}
	
	}

