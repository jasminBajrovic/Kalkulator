package digitron;

import java.util.Stack;

public class InternaLogika 
{
	static Stack<Double> unos = new Stack<Double>(); //Stack u kome pamtimo brojeve
	static Stack<String> operacije = new Stack<String>(); //Stack za operacije
	static int broj;
	static double zadnjiClan; 

	public static String procesirajUnos(char unos)
	{
		try
		{
			broj = Integer.parseInt(String.valueOf(unos)); //Pokusavamo da procitamo broj

			if (InternaLogika.unos.isEmpty()) //Ako uspemom i ako nemamo ni jedan, ide na stack
			{
				InternaLogika.unos.push((double)broj); 
			} else
			{
				//Ako vec imamo nesto na stacku, onda to pomeramo za desetku 
				//i dodajemo novi broj (npr, imali 2, kucamo 1, dobijamo 2*10+1 = 21)
				InternaLogika.unos.push(InternaLogika.unos.pop()*10 + broj);
			}
			return ukloniNule(String.valueOf(InternaLogika.unos.peek()));
			//Vracamo unos provucen kroz metodu koja nam brise pratece nule, ako ih ima
		} catch (NumberFormatException e)
		{
			String izlaz = "0"; //Ako nije broj, nadjemo se ovde
			                   //odmah pripremimo 0 za izlaz, u slucaju da ne uradimo
			                   //ama nista
			if (InternaLogika.unos.size() >= 2) //Ako imamo nekih brojeva na stacku
			{
				if (unos == '=') //pritisnuli smo jednako
				{
					if (!InternaLogika.operacije.isEmpty()) //Imamo neku operaciju vec 
					{
						if (InternaLogika.zadnjiClan == 0) //ako nismo vec
						{                         //pamtimo zadnji clan da bi 
							                      //mogli da ponavljamo =
							InternaLogika.zadnjiClan = InternaLogika.unos.peek();
						} else
						{
							//Ako ga imamo upamcenog, onda ga vracamo na stack
							InternaLogika.unos.push(zadnjiClan);
						}
						//Pogledamo koja je operacija u pitanju ali je ne izvrsavamo
						InternaLogika.izvrsiOperaciju(InternaLogika.operacije.peek());
						//Spremamo izlaz
						izlaz = String.valueOf(InternaLogika.unos.peek());
					}
				} else //Nismo pritisnuli jednako
				{
					InternaLogika.zadnjiClan = 0; //anuliramo clan koji nam
					                        //sluzi za ponavljanje =
					InternaLogika.operacije.push(String.valueOf(unos));
					//postavljamo operaciju koja je pritisnuta na stack
					
					if (InternaLogika.unos.size() < 2) 
					{
						//Ako imamo manje od 2 clana na stacku nemamo sta da radimo,
						//ali saljemo zadnji podatak sa stacka na prikaz
						izlaz = String.valueOf(InternaLogika.unos.peek()); 
					} else
					{
						int indeksOperacije;
						//Sada nas intresuje da li treba da izvrsimo zadnju ili predzadnju
						//operaciju
						if (InternaLogika.operacije.size() > 1)
						{
							indeksOperacije = operacije.size() - 2;
						} else
						{
							indeksOperacije = operacije.size() - 1;
						}
						
						//Izvrsavamo je i postavljamo izlaz
						InternaLogika.izvrsiOperaciju(InternaLogika.operacije.get(indeksOperacije));
						InternaLogika.operacije.remove(indeksOperacije);
						izlaz = String.valueOf(InternaLogika.unos.peek());
					}
					
					InternaLogika.unos.push((double)0); //Unosimo nulu koja ce da sluzi 
					                                 //za iduci broj, posle operacije
				}
			}
			return ukloniNule(izlaz); 
		}
	}

	public static void izvrsiOperaciju(String op)
	{
		double drugi; //treba nam za / i -
		switch (op)
		{
		case "+": 
			InternaLogika.unos.push(InternaLogika.unos.pop() + InternaLogika.unos.pop());  
			break;
		case "*":
			InternaLogika.unos.push(InternaLogika.unos.pop() * InternaLogika.unos.pop());
			break;
		case "-":
			drugi = InternaLogika.unos.pop();
			InternaLogika.unos.push(InternaLogika.unos.pop() - drugi);
			break;
		case "/":
			drugi = InternaLogika.unos.pop();
			InternaLogika.unos.push(InternaLogika.unos.pop() / drugi);
			break;
		}
	}
	public static String ukloniNule(String broj)
	{
		while (broj.contains(".")) //Dok broj sadrzi tacku
		{
			if (broj.endsWith("0")) 
			{
				//brisi pratece nule
				broj = broj.substring(0, broj.length()-2); 
			}else if (broj.endsWith("."))
			{
				//Ako smo stigli do tacke (npr. bilo 8.00, sada je 8.)
				//ukloni i nju
				broj = broj.substring(0, broj.length()-2);
			} else
			{
				//Nesto drugo nam je na kraju, npr 8.2, to je ok, vracamo broj
				return broj;
			}
		}
		return broj; //Ako ne sadrzi tacku nemamo nule koje bi brisali pa samo
		             //vracamo br :) 
	}


	public static String ocisti(boolean sve)
	{
		if (!InternaLogika.unos.isEmpty())
		{
			if (sve)
			{
				InternaLogika.unos.clear(); 
				InternaLogika.operacije.clear();
			} else
			{
				InternaLogika.unos.pop(); 
				InternaLogika.unos.push((double)0);
			}
		}
		return "0";
	}
}
