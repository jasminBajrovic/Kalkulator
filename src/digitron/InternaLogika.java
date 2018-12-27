package digitron;

public class InternaLogika 
{
	static long unos; 
	
	public static void procesirajUnos(char unos)
	{
		try
		{
			int broj = Integer.parseInt(String.valueOf(unos));
			if (InternaLogika.unos == 0)
			{
				InternaLogika.unos = broj;
			} else
			{
				if (String.valueOf(InternaLogika.unos).length() <= 15)
				{
					InternaLogika.unos = InternaLogika.unos * 10 + broj;
				}
			}
			
		} catch (NumberFormatException e) 
		{
			return;
		}
	}
	
	public static void ocisti(boolean sve)
	{
		if (sve)
		{
			//Ovo jos nemamo ideje
		} else
		{
			InternaLogika.unos = 0;
		}
	}
}
