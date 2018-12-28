package digitron;

import java.util.Stack;

public class InternaLogika 
{
	static Stack<Long> unos = new Stack<Long>(); 
	
	public static void procesirajUnos(char unos)
	{
		try
		{
			long broj = Integer.parseInt(String.valueOf(unos));
			if (InternaLogika.unos.isEmpty())
			{
				InternaLogika.unos.push(broj);
			} else if (InternaLogika.unos.peek() == 0)
			{
				InternaLogika.unos.pop();
				InternaLogika.unos.push(broj);
			} else
			{
				if (String.valueOf(InternaLogika.unos.peek()).length() <= 15)
				{
					InternaLogika.unos.push(InternaLogika.unos.pop() * 10 + broj);
				}
			}
			
		} catch (NumberFormatException e) 
		{
			if (unos == '+')
			{
				if (InternaLogika.unos.size() == 1)
				{
					InternaLogika.unos.push((long)0);
				} else
				{
					InternaLogika.unos.push(InternaLogika.unos.pop() + 
							                InternaLogika.unos.pop());
				}
			}
		}
	}
	
	public static void ocisti(boolean sve)
	{
		if (sve)
		{
			//Ovo jos nemamo ideje
		} else
		{
			InternaLogika.unos.pop();
			InternaLogika.unos.push((long)0);
		}
	}
}
