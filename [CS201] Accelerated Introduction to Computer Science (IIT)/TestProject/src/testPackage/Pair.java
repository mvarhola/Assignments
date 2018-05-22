package testPackage;

public class Pair<T,S> {
	private T t;
	private S s;
	
	public Pair(T t, S s){
		this.t=t;
		this.s=s;
		
	}
	public T getFirst(){
		return t;
	}
	public S getSecond(){
		return s;
		
	}
	public String toString(){
		return t.toString()+" "+s.toString();
	}
	
	
}
