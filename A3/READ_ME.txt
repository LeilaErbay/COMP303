Student: Leila Erbay
ID: 260672158

Pin.java: creates a pin, confirms the old pin, validates a new pin  
	  Has been updated since A1
	 
	 Updates: use of char arrays instead of Strings
		 private test functions to test private methods 
		 public tester function used in jUnit file
	 
	 Note: tester prints true and false according to result of private metho               ds as well as value of test pin and the original pin 


PinMain.java: UI for pin

CharArrayGenerator.java: creates random char arrays used for testing
			 Used in Pin.java and PinTest.java

PinTest.java: uses jUnit to test public api of Pin.java also calls tester 
	      from Pin.java 

	      Note: tester prints out statements regarding testing

PinTestRunner.java: runs the tests of PinTest.java
