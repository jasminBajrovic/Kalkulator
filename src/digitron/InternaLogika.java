package digitron;

import java.util.Stack;


/*
 * ZADACI
 * 
 * Dodati ostale operacije 
 *        --Obratite paznju kod oduzimanja i deljenja. Nacin na koji radimo obrne redosled operanada. Ako smo uneli 23 + 2, 2 je zadnji element na stacku pa kada uradimo pop() + pop() mi, u stvari racunamo 2 + 23
 *          Nije problem kod sabiranja i mnozenja, ali za druge dve operacije to mora drugacije. Moj predolg je da u jednu promenljivu uradite prvi pop() pa onda pop() - promenljiva da bi redosled bio u redu
 * Dodati memoriju
 *        --Trebace nam jos jedno stanje u klasi, koje ce drzati vrednost. Kada korisnik pritisne M+ dodajemo sta god da je na displeju (sto ce da bude i zadnji element u steku) na to stanje. Mozemo koristit peek() da ne sklonimo vrednost sa
 *        stacka. M- ce, naravno da oduzme. MR cita vrednost (jednostavan predlog pop() da sklonimo sta je bilo zadnje na stacku pa push() da stavimo novu vrednost). MC je jednostavno stavi na 0
 * +/- 
 *      --Ovo dugme bi trebalo da obrne znak zadnjoj vrednosti. Predlog, peek() da vidimo je li pozitivna, ako je onda 0 - pop() i to samo push() na stack :) ako je neg onda mozemo Math.abs 
 * %
 *     --Procentni racun bi trebao da radi na slican nacin kao =. Kada se pritisne, nebitno je koju je operaciju pre korisnik uneo dobija se procentni racun. Npr 50 + 25 % bi iznosilo 12.5 kao i 50 * 25 %
 *       Predlog je da, kao i jednako, proveri da li ima dva elementa u stacku i onda samo izracuna procenat, bez obzira na zadnnju operaciju
 * .   
 *     --Ovo bude zabavno :) Treba da podelimo posao na dva dela. Prvo, da napravimo da program radi sa brojevima koji nisu celi, pa tek onda da vidimo kako da ih i unosimo kao takve. Prvi deo je laksi. Ako stavimo da su nas stack i
 *       nase stanje za ulaz double program ce da radi u redu. No, ostalo nam je da mozemo da i unesemo broj koji nije ceo.
 *       Ideja: kada se pomeramo pre zareza racunamo prosliBroj * 10 + noviBroj. Pa ako imamo 5 a kucamo 2 dobijamo 5 * 10 + 2 tj 52. Ako posle kucamo 1 dobijamo 52 * 10 + 1 = 521. Kada hocemo da se krecemo iza tacke, recimo, 
 *       imamo 5.0 i kucamo 2. Da bi dobili sto zelimo racunamo 5 + 2 * 0.1 i dobijamo 5.2. Kucamo 3, treba da racunamo 5.2 + 3 * 0.01 da bi dobili 5.23. Formula nam je, znaci, prosliBroj + noviBroj * 10 na minus tacno koliko je odmaknut od tacke
 *       Za prvi broj iza tacke 10 na minus prvu, zatim na minus drugu i tako dalje. Nacin na koji bih ja ovo resio je da bi imao stanje koje mi kaze da li sam iza zareza, recimo neki int koji je na 0 kada smo sa leve strane zareza. Kada
 *       trebamo da predjemo na desnu, umanjimo ga i pre no sto racunamo nas novi broj na liniji 49 proverimo je li to stanje manje od nule. Ako jeste onda koristimo ovu drugu forumulu i taman mozemo da korisitimo to stanje jer ce ono biti 
 *       -1 za prvu cifru, pa dignemo 10 na njega (Math.pow(10, nekoStanje)) i umanjimo ga opet. Kada korisnik pritisne neku operaciju ili = i tome slicno samo ga stavimo ponovo na 0 :)  
 */



public class InternaLogika 
{
	static Stack<Long> unos = new Stack<Long>(); 
	static char zadnjaOperacija = '0';
	static int broj; 

	public static String procesirajUnos(char unos)
	{
		try
		{
			broj = Integer.parseInt(String.valueOf(unos)); 

			if (InternaLogika.unos.isEmpty()) 
			{
				InternaLogika.unos.push((long)broj); 
			} else
			{
				InternaLogika.unos.push(InternaLogika.unos.pop()*10 + broj);
			}
			return String.valueOf(InternaLogika.unos.peek()); 
		} catch (NumberFormatException e)
		{
			String izlaz = "0"; 
			if (!InternaLogika.unos.isEmpty())
			{
				switch (unos) 
				{
				case '+': 
					zadnjaOperacija = '+'; 
					if (InternaLogika.unos.size() < 2) 
					{
						izlaz = String.valueOf(InternaLogika.unos.peek()); 
					} else
					{
						InternaLogika.unos.push(InternaLogika.unos.pop() + InternaLogika.unos.pop());
						izlaz = String.valueOf(InternaLogika.unos.peek());  
					}
					InternaLogika.unos.push((long)0); 
					break;
				case '=':
					if (zadnjaOperacija != '0') 
					{
						switch (zadnjaOperacija) 
						{
						case '+':
							if (InternaLogika.unos.size() == 2) 
							{

								InternaLogika.unos.push(InternaLogika.unos.pop() + InternaLogika.unos.pop()); 
							} 
							izlaz = String.valueOf(InternaLogika.unos.peek());
							InternaLogika.unos.push((long)0); 
							break;
						}
						break;
					}
				}

			} 
			return izlaz;
		}
	}

	public static String ocisti(boolean sve)
	{
		if (!InternaLogika.unos.isEmpty())
		{
			if (sve)
			{
				InternaLogika.unos.clear(); 
				InternaLogika.zadnjaOperacija = '0';
			} else
			{
				InternaLogika.unos.pop(); 
				InternaLogika.unos.push((long)0);
			}
		}
		return "0";
	}
}
