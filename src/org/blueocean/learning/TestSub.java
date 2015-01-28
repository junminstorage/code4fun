package org.blueocean.learning;


class TestSub {
	private TestSub() {}
	private  int i;
	private static class Holder {
		private final static TestSub INSTANCE = new TestSub();
		private void test(){
			
		}
	}
	
	public TestSub getInstance(){
		new Holder().test();
		return Holder.INSTANCE;
	}
	
	public static void main(String[] argus){
		Holder h = new Holder();
	}
	private static class HolderSub extends Holder{
		public void test2(Holder h){
			
			h.test();
		}
	}
}
