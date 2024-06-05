package fuzzyLogicOdev1;

import java.io.File;
import java.net.URISyntaxException;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class TrafikIsik {
	private final FIS fis;
	private double yesilArac;
	private double kirmiziArac;
	
	public TrafikIsik(double yesilArac, double kirmiziArac) throws URISyntaxException
	{
		this.yesilArac = yesilArac;
		this.kirmiziArac = kirmiziArac;
		
		File file = new File(getClass().getResource("TrafikIsik.fcl").toURI());
		fis = FIS.load(file.getPath(),true);
		
		fis.setVariable("yesilArac", yesilArac);
		fis.setVariable("kirmiziArac", kirmiziArac);
		fis.evaluate();
		
	}
	public double getSure(double yesilArac, double kirmiziArac)
	{
		fis.setVariable("yesilArac", yesilArac);
		fis.setVariable("kirmiziArac", kirmiziArac);
		fis.evaluate();
		return Math.round(fis.getVariable("yesilSure").getValue());
	}
	public void grafikCiz()
	{
		JFuzzyChart.get().chart(fis);
		JFuzzyChart.get().chart(fis.getVariable("yesilSure").getDefuzzifier(),"Yeşil Yanma Süresi",true);
	}
	
	public String toString() {
		return "Yesil Isik Yanma Suresi(sn): " +Math.round(fis.getVariable("yesilSure").getValue())+" sn";
	}
	public FIS getModel() {
		return fis;
	}
		
}
