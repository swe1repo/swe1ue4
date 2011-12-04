package at.swe1ue4.plugins;

public class TestPlugin implements PluginInterface {

	@Override
	public int rateString(String[] text) {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public String getMessageForString(String[] text) {
		// TODO Auto-generated method stub
		return "POSITIVE\n";
	}

}
