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
	static Stack<Long> unos = new Stack<Long>(); //Stack u koji cemo da unosimo brojeve
	static char zadnjaOperacija = '0'; //Treba nam da znamo sta je bila zadnja pritisnuta operacija ako korisnik pritisne =
	static int broj; //Stanje za ulaz 

	public static String procesirajUnos(char unos)
	{
		try
		{
			broj = Integer.parseInt(String.valueOf(unos)); //Pokusavamo da parsiramo simbol koji smo dobili kao broj

			if (InternaLogika.unos.isEmpty()) //Ako smo uspeli, proverimo da li imamo vec nesto u steku
			{
				InternaLogika.unos.push((long)broj); //Ako nemamo, samo unosimo direktno broj koji smo dobili
			} else
			{
				InternaLogika.unos.push(InternaLogika.unos.pop()*10 + broj); //Ako nije prazan, onda vadimo zadnji broj sa stacka, pomeramo ga na desetine i dodajemo novi. Pa, ako je
				//bio broj 5 na stacku i korisnik kuca 2 dobijamo 5 * 10 + 2 = 52 :) 
			}
			return String.valueOf(InternaLogika.unos.peek()); //Vracamo zadnju vrednost sa stacka da bi se prikazala na ekranu naseg digitrona
		} catch (NumberFormatException e)
		{
			String izlaz = "0"; //Ako nismo uspeli da parsiramo broj u liniji 42 onda dolazimo ovde. Pripremamo nesto da posaljemo nazad za ekran, za svaki slucaj stavimo odmah 0 ako se bas nista ne dogodi
			//Npr, ako korisnik odmah pri pokretanju programa pritisne + apsolutno nista ne radimo od ovog koda dole.
			if (!InternaLogika.unos.isEmpty())
			{
				switch (unos) //Proveravamo sta je unos
				{
				case '+': 
					zadnjaOperacija = '+'; //Postavljamo informaciju o operaciji koju radimo
					if (InternaLogika.unos.size() < 2) //Imamo li dva broja za sabiranje na stacku?
					{
						izlaz = String.valueOf(InternaLogika.unos.peek()); //Ako nemamo, samo ostavljamo taj jedan broj koji imamo za prikaz
					} else
					{
						InternaLogika.unos.push(InternaLogika.unos.pop() + InternaLogika.unos.pop()); //U slucaju da imamo dva broja radimo tri stvari u jednoj liniji
						//Npr ako na stacku imamo 3 i 2, prvo ce se izvrsiti popovi tako da cemo imati
						//InternaLogika.unos.push(3 + 2); i posto pop() uklanja vrednost sa stacka, stack ce da bude prazan
						//Zatim ce push samo da gurne zbir nazad na stack
						izlaz = String.valueOf(InternaLogika.unos.peek());  //Za ekran samo jos pogledamo sta nam je upravo dobijeni zbir
					}
					InternaLogika.unos.push((long)0); //Zavrsili sabiranje ili ne, ako smo pritisnuli + moramo da predejmo na novi broj
					//Nas program uvek radi sa zadnjim brojem na stacku. Kada hocemo da unesemo, na primer 23 + 5. Mi kada kucamo 2 to postaje prvi broj na stacku. Kada kucamo 3 po formuli
					//na liniji 49 dobijamo 2*10 + 3 tj. 23. Zatim, pritisnemo + i u tom momentu guramo 0 na stack. Pa na stacku imamo 23 i 0. Zatim unesemo 5, pa imamo 0 * 10 + 5 tj 5 :)
					break;
				case '=':
					if (zadnjaOperacija != '0') //Proveravamo je li uposte neka operacija pritisnuta
					{
						switch (zadnjaOperacija)  //Ako jeste, gledamo koja je
						{
						case '+':
							if (InternaLogika.unos.size() == 2) //Proveravamo imamo li dovoljno brojeva za racunanje
							{

								InternaLogika.unos.push(InternaLogika.unos.pop() + InternaLogika.unos.pop()); //Racunamo
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
				InternaLogika.unos.clear(); //Ako cistimo sve brisemo skroz stack i nuliramo zadnju operaciju
				InternaLogika.zadnjaOperacija = '0';
			} else
			{
				InternaLogika.unos.pop(); //Ako brisemo samo zadnju gresku jedan pop() to resi i samo postavimo nulu na mesto te vrednosti
				InternaLogika.unos.push((long)0);
			}
		}
		return "0";
	}
}
