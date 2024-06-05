package fuzzyLogicOdev1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class Program {

	public static void main(String[] args) throws URISyntaxException, IOException{
		Scanner in = new Scanner(System.in);
		double yesilArac,kirmiziArac;
		
		do
		{
			System.out.print("Yeşil Işık Yanan Yoldaki Araç Sayısı [0-24]: ");
			yesilArac = in.nextDouble();
			System.out.print("Kırmızı Işık Yanan Yolda Bekleyen Araç Sayısı [0-24]: ");
			kirmiziArac = in.nextDouble();
		}
		while(yesilArac > 24 || kirmiziArac > 24);
		
		TrafikIsik trafikIsik = new TrafikIsik(yesilArac, kirmiziArac);
		try
		{
			var kurallar = trafikIsik.getModel().getFunctionBlock("TrafikIsik").getFuzzyRuleBlock("kuralBlok1").getRules();
			for(var kural : kurallar) {
				if(kural.getDegreeOfSupport() > 0)
					System.out.println(kural);
			}
			System.out.println("Yeşil Işık Yanma Süresi(sn) : " + trafikIsik.getSure(yesilArac, kirmiziArac) + " sn");
			JFuzzyChart.get().chart(trafikIsik.getModel());
			JFuzzyChart.get().chart(trafikIsik.getModel().getVariable("yesilSure").getDefuzzifier(),"Yesil Yanma Suresi",true);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}
