package usedeprecated;
class LegacyAPI{
	@Deprecated
	void oldFeature()
	{
		System.out.println("OLD FEATURE");
	}
	void newFeature() {
		System.out.println("NEW FEATURE");
	}
}

public class UseDeprecated {
	public static void main(String args[]) {
		LegacyAPI api= new LegacyAPI();
		api.oldFeature();
		api.newFeature();
	}
}
