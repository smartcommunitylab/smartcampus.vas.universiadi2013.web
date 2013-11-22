package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import eu.trentorise.smartcampus.universiadi.model.Domanda;
import eu.trentorise.smartcampus.universiadi.model.GPS;
import eu.trentorise.smartcampus.universiadi.model.SportObj;

@Controller("sportController")
public class SportController {

	@Autowired
	MongoTemplate db;

	@PostConstruct
	public void init() {
		// mListaAllSport = ContainerSport.getSport();
		db.dropCollection(SportObj.class);

		SportObj x = new SportObj(
				"Alpine",
				0,
				"La disciplina sportiva che cerca di rispondere alla domanda \u00abvediamo chi arriva primo a valle!\u00bb Uno dei pilastri del programma sportivo delle Universiadi, lo sci alpino \u00e8 la disciplina che vanta il maggior livello di partecipazione della manifestazione e solitamente quasi tutti i paesi presenti schierano almeno un atleta al cancelletto di partenza di una delle gare di discesa libera, super G, slalom gigante e slalom, concorrendo anche all\u2019assegnazione della medaglia della \u00abcombinata\u00bb che riunisce almeno tre risultati delle quattro specialit\u00e0. La prima vincitrice universitaria di una medaglia d\u2019oro in una specialit\u00e0 dello sci alpino fu C\u00e9cile Prince, la quale conquisto l\u2019oro per la Francia nel SuperG organizzato sulle piste di Chamonix durante la prima edizione delle Universiadi invernali del 1960.\n\nDurante le Universiadi di Trentino 2013, le gare dello sci alpino saranno ospitate dalla piste della Val di Fassa, in particolare la pista \u00abAloch\u00bb di Pozza di Fassa per lo Slalom e lo Slalom Gigante e la pista \u00abNuova Cima Uomo\u00bb del Passo San Pellegrino per la Discesa Libera e il SuperG.",
				"Uomini: Discesa libera, Super G, Slalom Gigante, Slalom, Combinata*\nDonne: Discesa libera, Super G, Slalom Gigante, Slalom, Combinata*\n*Classifica combinata basata sui risultati di almeno 3 eventi",
				"Ogni NUSF (Federazione Nazionale Sportiva Universitaria) pu\u00f2 schierare un massimo di 18 atleti per le gare di sci alpino, ma non \u00e8 possibile avere pi\u00f9 di 12 atleti dello stesso sesso. Inoltre, ogni gara prevede un massimo di 6 atleti appartenenti allo stesso paese.");
		x.setNomeEn("sci alpino");
		GPS[] listGps = new GPS[2];
		listGps[0] = new GPS(46.39306803994184, 11.799665411958777);
		listGps[0].setTitle("Passo San Pellegrino - Passo San Pellegrino");
		listGps[1] = new GPS(46.42895910089672, 11.695014369936438);
		listGps[1].setTitle("Pozza di Fassa - Ski Stadium Val di Fassa");

		x.setGeolocations(listGps);

		db.save(x);
		x = new SportObj(
				"Cross",
				1,
				"La combinata nordica \u00e8 stata uno dei pilastri fondamentali del programma sportivo dell\u2019Universiade, caratterizzandolo fin dalla sua prima edizione tenutasi a Chamonix in Francia nel 1960, nonostante alcune gare non siano state organizzate in un paio delle 25 edizioni delle Universiadi svoltesi fino a oggi.\n\nLa combinata nordica richiede la padronanza di due specialit\u00e0: lo sci di fondo e il salto con gli sci. I combinatisti sono ottimi saltatori e fortissimi fondisti che si sfidano sul trampolino e sulle piste di fondo, trasformando con un sistema particolare il distacco ottenuto in una o l\u2019altra gara in tempo o metri di ritardo. La tipica gara di combinata prevede prima una gara di salto con gli sci e poi, in base ai punti ottenuti, una gara di fondo con partenza \u00abGundersen\u00bb o a inseguimento, ovvero con il ritardo ottenuto nella gara precedente. Questo formato pu\u00f2 anche essere invertito, realizzando prima la gara di fondo con partenza in linea e poi quella di salto, trasformando in questo caso i secondi di ritardo registrati nella gara di fondo in punti (ovvero metri) di distacco per la gara di salto. Gli eventi di combinata nordica sono previsti solo per gli uomini, dal trampolino piccolo, sulla distanza di 10 km e con tre formati di gara: gundersen individuale, mass-start individuale e gara a squadre. Come nel caso del salto con gli sci, gli atleti sovietici, russi e giapponesi sono sempre stati tra i migliori interpreti di questa disciplina, anche se uno degli atleti di maggior successo internazionale e presente nell\u2019albo d\u2019oro delle Universiadi \u00e8 George Hettich (GER), vincitore di una medaglia d\u2019oro all\u2019Universiade del 2005 e campione olimpico in occasione dei Giochi Olimpici Invernali di Torino 2006.\n\nLe sedi delle gare di sci nordico saranno i trampolini e le piste della Val di Fiemme, una delle culle italiane dello sci nordico e gi\u00e0 sede di ben tre campionati del mondo (1991, 2003, 2013). Nel dettaglio, le gare di salto con gli sci si svolgeranno sul trampolino HS106 e HS134 di Predazzo, mentre le gare di sci di fondo si correranno sulle meravigliose piste iridate di Lago di Tesero.",
				"Uomini: Discesa libera, Super G, Slalom Gigante, Slalom, Combinata*\nDonne: Discesa libera, Super G, Slalom Gigante, Slalom, Combinata*\n*Classifica combinata basata sui risultati di almeno 3 eventi",
				"Ogni NUSF (Federazione Nazionale Sportiva Universitaria) può schierare un massimo di 18 atleti per le gare di sci alpino, ma non è possibile avere più di 12 atleti dello stesso sesso. Inoltre, ogni gara prevede un massimo di 6 atleti appartenenti allo stesso paese.");
		x.setNomeEn("combinata nordica");
		listGps = new GPS[2];
		listGps[0] = new GPS(46.282421123514105, 11.523825189299174);
		listGps[0].setTitle("Tesero - Lago di Tesero");
		listGps[0].setStreet("via Stazione 4");
		listGps[1] = new GPS(46.32887671502965, 11.601680676500745);
		listGps[1].setTitle("Predazzo - Ski Jumping Stadium");
		x.setGeolocations(listGps);

		db.save(x);
		x = new SportObj(
				"Curling",
				2,
				"Il modo pi\u00f9 facile per descrivere questa disciplina sicuramente \u00e8 quello di paragonarlo al gioco delle bocce, solo in versione decisamente pi\u00f9 invernale dato che viene giocato su un campo completamente ghiacciato.\n\nIn una partita di curling, due squadre composte da quattro giocatori lanciano delle pietre di granito di quasi 20 kg (dette stones) su una lastra di ghiaccio con l\u2019obiettivo di avvicinarle il pi\u00f9 possibile a un\u2019area di destinazione chiamata house e costituita da quattro anelli concentrici. Durante ogni intervallo di gioco, ovvero una mano, ogni squadra ha a disposizione otto stones, due per ogni giocatore. Una partita intera pu\u00f2 durare 8 o 10 mani. In ogni mano sono assegnati dei punti in base a quante stones della stessa squadra si trovano pi\u00f9 vicine al centro della house, esattamente come avviene nelle bocce. L\u2019obiettivo finale \u00e8 quello di conquistare pi\u00f9 punti della squadra avversaria.\n\nParticolarit\u00e0 di questo sport \u00e8 la cosiddetta fase di sweeping, utilizzata dai giocatori per riscaldare il ghiaccio con delle scope e modificare la traiettoria o la velocit\u00e0 della stone. Grandissima rilevanza ha anche la strategia assunta da ogni squadra che deve riuscire a proteggere le proprie stones da quelle avversarie posizionandole con ingegno e maestria nell\u2019area di house.\n\nLa prima apparizione del curling nel programma delle Universiadi avvenne a Tarvisio nel 2003, dove fu presentato come disciplina opzionale. Il successo fu immediato: nella prima edizione 6 squadre maschili e 5 squadre femminile battagliarono per l\u2019oro, attirando un buon numero di spettatori e risvegliando molto interesse.\n\nPer questi motivi, il curling \u00e8 diventato una degli sport obbligatori del programma delle Universiadi, registrando anno dopo anno la presenza di squadre di altissimo livello, in grado perfino di conquistare medaglie importanti in altre competizioni internazionali.\n\nDurante le Universiadi Invernali Trentino 2013, saranno organizzati due tornei di curling: uno femminile e uno maschile, con la presenza totale di 10 squadre cadauno.\n\nLa sede del torneo di curling sar\u00e0 lo spazioso Ice Rink di Baselga di Pin\u00e9, in grado di ospitare fino a 1800 spettatori che potranno inoltre dividersi tra le partite \u00abragionate\u00bb del curling e l\u2019emozione delle gare ad alta velocit\u00e0 del pattinaggio su ghiaccio, organizzate allo stadio del ghiaccio di Baselga di Pin\u00e9.",
				"Torneo femminile e torneo maschile",
				"Uomini: ogni torneo prevede la partecipazione di un massimo di 10 squadre, ognuna composta da 5 giocatori (quattro pi\u00f9 una riserva).\nDonne: ogni torneo prevede la partecipazione di un massimo di 10 squadre, ognuna composta da 5 giocatrici (quattro pi\u00f9 una riserva).\n\nOgni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 chiedere di inserire una squadra maschile ed una femminile.\n\nIn caso siano iscritte pi\u00f9 squadre di quante previste, le squadre partecipanti saranno selezionate in base ai risultati conseguiti in altre edizioni delle Universiadi o i risultati di eventuali partite di qualificazione.");
		x.setNomeEn("curling");
		listGps = new GPS[1];
		listGps[0] = new GPS(46.126572271845454, 11.251979685399577);
		listGps[0].setTitle("Ice Rink Piné Stadium");
		x.setGeolocations(listGps);
		db.save(x);

		x = new SportObj(
				"Figure",
				3,
				"La grazia e la potenza riuniti in uno sport, combinati con musica, ballo e in alcuni casi la capacit\u00e0 di eseguire tutti questi movimenti in perfetta sincronia in coppia: una vera e propria forma d\u2019arte.\n\nTutto questo \u00e8 il pattinaggio di figura, uno degli sport che solitamente riesce a riscuotere il maggior successo di pubblico dell\u2019Universiade, non solo grazie alle gare vere e proprie, ma anche grazie all\u2019esibizione prevista alla conclusione del programma sportivo dove la mancanza dello stress da competizione permette agli atleti di dimostrare pienamente il proprio potenziale, prendendosi magari anche qualche rischio in pi\u00f9 e regalando incredibili emozioni agli spettatori.\n\nIn occasione della prima Universiade, tenutasi nel 1960 a Chamonix in Francia, 6 uomini e 7 donne presero parte alle competizioni individuale di pattinaggio artistico e i primi due vincitori furono il francese Alain Calmat e la cecoslovacca Eva Grozajoeva. In totale, fino all\u2019edizione di quest\u2019anno, il programma di 23 Universiadi su 25 ha previsto delle gare di pattinaggio di figura.\n\nGrandi campioni e campionesse del pattinaggio di figura hanno partecipato e vinto importanti medaglie alle Universiadi, come la russa Irina Slutskaya o le coppie cinesi Zhang-Zhang e Shen-Zhao.\n\nDurante le Universiadi Invernali Trentino 2013, il programma di pattinaggio artistico prevede 4 eventi in cui saranno in palio importanti medaglie: prova maschile, prova femminile, prova a coppie e danza su ghiaccio.\n\nTutte queste gare saranno organizzate allo stadio Palaghiaccio di Trento, una costruzione in grado di ospitare pi\u00f9 di 1.030 persone e che risulter\u00e0 essere la cornice perfetta per delle gare che saranno sicuramente vere e proprie opere d\u2019arte.",
				"Individuale maschile, individuale femminile, danza su ghiaccio e coppie",
				"Individuale maschile: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di tre atleti\nIndividuale femminile: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di tre atlete\nCoppie: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di tre coppie\nDanza su ghiaccio: ogni paese potr\u00e0 iscrivere un massimo di tre coppie\nIn caso il numero di iscritti per le prove individuali maschili e femminili superi le 30 unit\u00e0, il comitato tecnico della FISU potrebbe decidere di ridurre il limite massimo per paese a due atleti e atlete. I vincitori di medaglie a Erzurum 2011 avranno la possibilit\u00e0 di partecipare alle gare senza essere considerati nella quota prevista per ogni paese.");
		x.setNomeEn("pattinaggio artistico");

		listGps = new GPS[1];
		listGps[0] = new GPS(46.04093342207616, 11.122541427612305);
		listGps[0].setTitle("Trento - Ice Stadium");
		listGps[0].setStreet("Via Fersina (Loc. Ghiaie)");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"Freestyle",
				4,
				"Lo sci acrobatico o freestyle \u00e8 uno degli sport invernali pi\u00f9 giovani e ricalca pi\u00f9 o meno gli eventi di snowboard, praticandoli per\u00f2 con degli sci ai piedi piuttosto che con la tavola da snowboard.\n\nEssendo un sport in forte sviluppo e crescita soprattutto in ambiente universitario, il freestyle \u00e8 stato introdotto per la prima volta nel programma delle Universiadi durante l\u2019edizione austriaca di Innsbruck tenutasi nel 2005.\n\nAllora, si decise di presentare la specialit\u00e0 dello ski cross come disciplina dimostrativa, preparando delle apposite piste sulle quali gruppi di quattro o sei atleti si affrontarono per determinare chi fosse il pi\u00f9 veloce a scendere da un pendio caratterizzato da continui salti e ostacoli.\n\nIl successo di questo primo tentativo di introduzione conferm\u00f2 il free style come disciplina interessante per la FISU che decise di riproporre lo ski cross in un\u2019altra edizione delle Universiadi e di accompagnarlo con una nuova specialit\u00e0 freestyle (mogouls) in occasione di Erzurum 2011.\n\nAlle Universiadi Invernali Trentino 2013, il freestyle sar\u00e0 una delle due discipline facoltative scelte dagli organizzatori e le specialit\u00e0 presenti saranno due: ski cross e slope style.\n\nIn entrambi i casi, l\u2019obiettivo sar\u00e0 quello di scendere il pi\u00f9 rapidamente possibile da un pendio caratterizzato da salti impegnativi e da diversi altri ostacoli, ma con una principale differenza: nello ski cross gli atleti dovranno scendere in gruppi di quattro o sei, cercando quindi di evitare pericolosi contatti con gli avversari; mentre nello slope style un gruppo di giudici valuter\u00e0 con grandissima attenzione le acrobazie realizzate dagli sciatori durante la loro discesa a valle, premiando cos\u00ec i pi\u00f9 bravi e spettacolari.\n\nEsattamente come le gare di snowboard, anche quelle di freestyle si terranno sulle meravigliose piste del Monte Bondone, con la pista \u00abLavaman\u00bb teatro dello ski cross e dello slope style.",
				"Uomini: Ski cross, Slope style\nDonne: Ski cross, Slope styl",
				"Uomini: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di sei atleti, quattro dei quali potranno prendere parte alle singole gare.\nDonne: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di sei atlete, quattro delle quali potranno prendere parte alle singole gare.");
		x.setNomeEn("sci acrobatico");
		listGps = new GPS[1];
		listGps[0] = new GPS(46.02759, 11.04461);
		listGps[0].setTitle("Monte Bondone - Monte Bondone");
		x.setGeolocations(listGps);

		db.save(x);
		x = new SportObj(
				"Ice",
				5,
				"Sport di squadra per eccellenza delle Universiadi, l\u2019hockey richiede atleti caratterizzati da forza, potenza, precisione e velocit\u00e0, oltre ad un fortissimo spirito di squadra.\n\nDisciplina di grandissimo successo in tutto il Nord America e in generale in quasi tutta Europa, l\u2019hockey \u00e8 considerato lo sport su ghiaccio pi\u00f9 conosciuto al mondo. Uno sport dove il contatto fisico tra i giocatori \u00e8 all\u2019ordine del giorno e garantisce partite emozionanti e senza quasi un attimo di respiro.\n\nNella storia delle Universiadi, il primo torneo di hockey fu organizzato nel 1962 durante la 2^ Universiade di Villars in Svizzera. Allora, quattro squadre presero parte al torneo maschile e la prima nazione a conquistare l\u2019oro fu la Cecoslovacchia. Dal 1962, l\u2019hockey \u00e8 stato presente nel programma delle Universiadi per ben 21 volte e a partire dall\u2019Universiadi del 2009 di Harbin (Cina) presenta anche il torneo femminile.\n\nIn occasione delle Universiadi di Erzurum in Turchia, ben 126 hockeiste provenienti da 6 paesi diversi si sono affrontate nel torneo femminile, dimostrando il successo di questo sport anche \u00abin rosa\u00bb.\n\nMoltissimi atleti che hanno preso parte alle Universiadi, hanno avuto modo di sfondare a livello internazionale, entrando a far parte di importanti squadre e conquistando grandi successi con le rispettive nazionali a Mondiali e Olimpiadi.\n\nDurante le Universiadi Invernali Trentino 2013, saranno organizzati i tornei femminile e maschile. Le partite del primo si svolgeranno nello stadio di Pergine Valsugana, mentre quelle del torneo maschile si svolgeranno negli stadi di Cavalese e Alba di Canazei.",
				"Torneo femminile e torneo maschile",
				"Uomini: il torneo riservato agli uomini prevede la partecipazione di un massimo di 12 squadre. Le squadre saranno accettate in base ai punteggi FISU e IIHF registrati al momento dell\u2019iscrizione. Uno dei posti nel torneo \u00e8 riservato alla squadra di casa (nel caso di Trentino 2013, la squadra italiana). Ogni squadra dovr\u00e0 essere composta da 20 giocatori e 2 portieri.\nDonne: il torneo riservato alle donne prevede la partecipazione di un massimo di 8 squadre. Le squadre accettate in base ai punteggi FISU e IIHF registrati al momento dell\u2019iscrizione sono risultate 6 avendo la squadra di casa espresso parere negativo alla partecipazione. Ogni squadra potr\u00e0 essere composta da un massimo di 20 giocatori e 2 portieri.");
		x.setNomeEn("hockey");
		listGps = new GPS[3];
		listGps[0] = new GPS(46.46179584266813, 11.784283101244108);
		listGps[0].setTitle("Alba di Canazei - Gianmario Scola Ice Hockey Arena");
		listGps[1] = new GPS(46.07192313475388, 11.235533631051503);
		listGps[1].setTitle("Pergine Valsugana - Ice Stadium");
		listGps[2] = new GPS(46.28583945210038, 11.464570111721242);
		listGps[2].setTitle("Cavalese - Municipal Ice Arena");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"Nordic",
				6,
				"Nel caso dello sci nordico, le distanze non sono percorse in verticale, ma piuttosto in orizzontale. Tre sono le specialit\u00e0 dello sci nordico: sci di fondo, salto con gli sci e combinata nordica. In ognuna di queste specialit\u00e0, gli atleti si sfidano su distanza o formati di gara diversi. Nel caso dello sci di fondo, si cambia anche stile, passando da quello \u00abclassico\u00bb al cosiddetto \u00abstile libero\u00bb o \u00abskating\u00bb\n\nLo sci di fondo \u00e8 stato uno dei pilastri fondamentali del programma sportivo dell\u2019Universiade, caratterizzandolo fin dalla sua prima edizione tenutasi a Chamonix in Francia nel 1960.\n\nLo sci di fondo \u00e8 passato dalle 4 gare previste nel 1960 agli 11 eventi medagliati dell\u2019edizione di quest\u2019anno, con 5 gare per uomini e donne, oltre che una gara \u00abmista\u00bb. In passato, gli atleti sovietici e russi sono stati i grandi dominatori di questa disciplina, conquistando moltissime medaglie sulle piste di tutto il mondo. Tra i nomi dei vincitori di medaglie alle Universiadi, quello che spicca pi\u00f9 di tutti \u00e8 sicuramente quello della polacca Justyna Kowalczyk, gi\u00e0 vincitrice di diversi titoli mondiali e olimpici oltre che di moltissime gare di coppa del mondo.\n\nLe sedi delle gare di sci di fondo saranno le piste della Val di Fiemme ed in particolare sulle meravigliose piste iridate di Lago di Tesero, una delle culle italiane e gi\u00e0 se",
				"Uomini: 10 km individuale TL, 15 km Skiathlon (TC+TL), Sprint TC, 30 km partenza in linea TC, staffetta 4x10 km (TC-TC-TL-TL)\nDonne: 5 km individuale TL, 10 km Skiathlon (TC+TL), Sprint TC, 15 km partenza in linea TC, staffetta 3x5 km (TC-TL-TL)\nMiste: Staffetta sprint",
				"Uomini: ogni NUSF (Federazione Nazionale Sportiva Universitaria) pu\u00f2 iscrivere un massimo di 8 concorrenti, 6 dei quali potranno partecipare alle gare individuali e 4 dei quali parteciperanno alla staffetta.\nDonne:ogni NUSF (Federazione Nazionale Sportiva Universitaria) pu\u00f2 iscrivere un massimo di 8 concorrenti, 6 dei quali potranno partecipare alle gare individuali e 3 dei quali parteciperanno alla staffetta.\nLa staffetta mista \u00e8 composta da due atleti, un uomo e una donna. Alla staffetta mista, ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di due squadre.\n\n ");
		x.setNomeEn("sci nordico");
		listGps = new GPS[1];
		listGps[0] = new GPS(46.282421123514105, 11.523825189299174);
		listGps[0].setTitle("Tesero - Lago di Tesero");
		listGps[0].setStreet("via Stazione 4");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"Shorttracking",
				7,
				"Una specie di \u00abfratello minore\u00bb del pattinaggio di velocit\u00e0 su ghiaccio, lo short track si differenzia dal proprio \u00abfratellone\u00bb per due principali motivi: innanzitutto si svolge su piste di 111,12 metri ed all\u2019interno della pista di ghiaccio 30x60m e in secondo luogo prevede che gli atleti si affrontino in gruppi o batterie di 4 o 8 concorrenti, riducendo di molto i margini di manovra dei pattinatori. Per questo, lo short track \u00e8 caratterizzato da altissime velocit\u00e0 e soprattutto da una lotta corpo a corpo tra gli atleti per prendere la testa della corsa e tagliare per primo il traguardo.\n\nL\u2019agilit\u00e0, la potenza e la velocit\u00e0 richieste in questo sport, oltre alla necessit\u00e0 di calcolare bene la tattica di gara, rendono lo short track una delle discipline pi\u00f9 emozionanti di tutto il programma delle Universiadi, con gare incerte fino all\u2019ultimo millimetro e distacchi minimi che possono fare la differenza tra la medaglia d\u2019oro e il quarto posto.\n\nLo short track fu introdotto per la prima volta nel programma delle Universiadi nel 1985, durante l\u2019edizione di Belluno. Allora, 10 squadre maschili e 9 squadre femminili presero parte alle competizioni, per un totale di 58 atleti. Le squadre pi\u00f9 forti furono fin da subito quella canadese e quella statunitense, portando a casa diversi ori.\n\nOggi per\u00f2 il vento sembra essere cambiato e gli atleti pi\u00f9 forti provengono quasi tutti dai paesi asiatici (Corea del Sud, Giappone e Cina). Molti atleti di short track vincitori di medaglie alle Universiadi hanno conquistato anche importanti titoli olimpici o mondiali. Tra di loro, si possono trovare i nomi delle due cinesi omonime Yang Yang (A) e Yang Yang (S), della coreana Min-Kyung Choi o dell\u2019italiana Marta Capurso.\n\nDurante le Universiadi Invernali Trentino 2013, le medaglie d\u2019oro in palio saranno otto, con sei gare individuali e due staffette. La sede delle gare di short track sar\u00e0 la stessa di quelle di pattinaggio di figura, ovvero al Palaghiaccio di Trento.",
				"Uomini: 500 m, 1000 m, 1500 m, staffetta 5000 m\nDonne: 500 m, 1000 m, 1500 m, staffetta 3000 m",
				"Ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere 6 atleti e 6 atlete per le gare individuali, ma non pi\u00f9 di 3 atleti e 3 atlete potranno partecipare a ogni gara. Se dovesse essere necessario, per motivi organizzativi, il numero di partecipanti potrebbe essere ridotto.\nPer le staffette, ogni paese potr\u00e0 iscrivere una squadra composta da 4 atleti, pi\u00f9 una riserva.\u2003");
		x.setNomeEn("pattinaggio");

		listGps = new GPS[1];
		listGps[0] = new GPS(46.04093342207616, 11.122541427612305);
		listGps[0].setTitle("Trento - Ice Stadium");
		listGps[0].setStreet("Via Fersina (Loc. Ghiaie)");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"Ski Jumping",
				8,
				"Assieme allo sci di fondo e alla combinata nordica, il salto con gli sci \u00e8 stato uno dei pilastri fondamentali del programma sportivo dell\u2019Universiade fin dalla sua prima edizione tenutasi a Chamonix in Francia nel 1960, nonostante le gare di combinata nordica e quelle di salto non siano state organizzate in un paio delle 25 edizioni delle Universiadi svoltesi fino a oggi.\n\nIl salto con gli sci ha assunto una posizione crescente nel programma delle Universiadi soprattutto grazie alla sua spettacolarit\u00e0 e al grande seguito che riscuote tra gli appassionati di sport invernali. Le prime gare di salto con gli sci delle Universiadi videro la partecipazioni di 16 atleti uomini provenienti da 7 paesi, con solo una gara in programma: quella dal trampolino piccolo. Da allora molte cose sono cambiate e oggi il programma delle Universiadi prevede ben cinque gare: tre per gli uomini (trampolino piccolo, trampolino grande e gara a squadre), una riservata alle donne (trampolino piccolo) e una a squadre miste. Gli storici dominatori delle gare di questa disciplina sono stati gli atleti sovietici, russi, sloveni e giapponesi.\n\nLe gare di salto con gli sci si svolgeranno sul trampolino HS106 e HS134 di Predazzo.",
				"Uomini: HS134 individuale - HS106 a squadre\nDonne: HS106 individuale\nMista (1 uomo + 1 donna): HS106",
				"Ogni NUSF (Federazione Nazionale Sportiva Universitaria) pu\u00f2 iscrivere un massimo di 8 atleti, 6 dei quali potranno partecipare alle gare individuali. La gara a squadre maschile dal trampolino grande richiede la partecipazione di squadre formate da 3 atleti ed \u00e8 previsto un massimo di una squadra per paese. La gara a squadre miste richiede la partecipazione di squadre formate da 2 atleti, un uomo e una donna, ed \u00e8 previsto un massimo di due squadre per paese");
		x.setNomeEn("salto");
		listGps = new GPS[1];
		listGps[0] = new GPS(46.32887671502965, 11.601680676500745);
		listGps[0].setTitle("Predazzo - Ski Jumping Stadium");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"Snowboarding",
				9,
				"Una delle discipline pi\u00f9 giovani del programma delle Universiadi, lo snowboard fu introdotto nel 1995 durante le Universiadi di Jaca in Spagna. Inizialmente fu considerato solo uno sport dimostrativo e 29 atleti in rappresentanza di 10 Paesi presero parte alle prime gare di snowboard di Jaca.\n\nIl grandissimo livello di spettacolarit\u00e0 della disciplina e la sua diffusione tra gli ambienti universitari gli permise per\u00f2 rapidamente di conquistare sempre pi\u00f9 spazio e ben presto lo snowboard divenne una delle discipline obbligatorie del programma, introducendo a partire dalle Universiadi del 1999 organizzate a Poprad-Tatry in Slovacchia i salti e l\u2019emozione del \u00abborder cross\u00bb. Pi\u00f9 tardi la specialit\u00e0 cambi\u00f2 nome, diventando Snowboard cross, ed entrarono a far parte del programma anche l\u2019halfpipe e lo slope style, specialit\u00e0 incredibilmente spettacolari.\n\nLo slope style \u00e8 un formato di gara che doveva realizzare il proprio debutto in occasione delle Universiadi di Erzurum nel 2011, ma fu annullata per le condizioni di vento instabili. L\u2019Universiade Invernale Trentino 2013 segner\u00e0 quindi il debutto ufficiale di questa specialit\u00e0 nel programma delle Universiadi.\n\nOltre a questa specialit\u00e0, il programma dell\u2019Universiade Invernale Trentino 2013 prevede anche gli eventi dello slalom gigante parallelo, dell\u2019halfpipe e dello snowboard cross, sia per uomini sia per donne.\n\nNello slalom gigante parallelo e nello snowboard cross sar\u00e0 il tempo a stabilire un vincitore, con gare caratterizzate da emozionanti sfide \u00abcorpo a corpo\u00bb incerte fino all\u2019ultimo.\n\nNell\u2019halfpipe e nello slope style invece, oltre al tempo, saranno le acrobazie degli atleti a fare la differenza, con salti e \u00abtrick\u00bb che verranno valutati attentamente da una giuria e risulteranno essere quindi l\u2019elemento chiave per la conquista di una medaglia.\n\nLa sede ospitante delle gare di snowboard sar\u00e0 il Monte Bondone, mettendo in mostra il meglio delle sue piste e del suo nuovo halfpipe e snowpark. In particolare, la gara del gigante parallelo si svolger\u00e0 sulla pista Canalon, quella di Snowboard cross sulla spettacolare discesa del \u00abLavaman\u00bb, mentre l\u2019halfpipe e lo slope style occuperanno il bellissimo \u00abSnow Park Monte Bondone\u00bb.",
				"Uomini e donne: Slalom gigante parallelo, Halfpipe, Snowboard cross, Slope style",
				"Uomini: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di 10 atleti, dei quali solo 4 potranno prendere parte a ogni evento in programma.\nDonne: ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di 8 atlete, delle quali solo 4 potranno prendere parte a ogni evento in programma.");
		x.setNomeEn("snowboard");
		listGps = new GPS[1];
		listGps[0] = new GPS(46.02759, 11.04461);
		listGps[0].setTitle("Monte Bondone - Monte Bondone");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"SpeedSkating",
				10,
				"Il pattinaggio di velocit\u00e0 prevede all\u2019interno del proprio programma delle competizioni su distanze brevi, adatte a pattinatori di grande potenza, e competizioni su distanze pi\u00f9 lunghe, adatte a pattinatori di maggiore resistenza.\n\nIn questo sport, gli atleti sono lasciati soli contro il cronometro, anche se solitamente hanno la possibilit\u00e0 di pattinare assieme a uno dei loro avversari, partendo per\u00f2 da lati opposti della pista lunga 400 metri. Inutile dire che il vincitore \u00e8 colui che riesce a segnare il miglior tempo sulla distanza prevista.\n\nIl pattinaggio di velocit\u00e0 su ghiaccio \u00e8 una delle due discipline facoltative inserite nel programma delle Universiadi di Trentino 2013, nonostante possa vantare una lunga storia all\u2019interno del programma di questa manifestazione.\n\nLa prima volta che il pattinaggio di velocit\u00e0 compar\u00ec in un programma sportivo delle Universiadi fu nel 1968 in occasione delle Universiadi di Innsbruck in Austria, presentando per\u00f2 gare destinate solo a pattinatori uomini. A partire dall\u2019edizione seguente (1970), furono organizzate anche gare per donne.\n\nIn totale, il pattinaggio di velocit\u00e0 su ghiaccio \u00e8 stato presente nel programma di otto Universiadi, registrando la vittoria e la partecipazione di grandissimi campioni vincitori anche di medaglie olimpiche e mondiali.\n\nTra i pi\u00f9 importanti, si ricordano i nomi dei coreani Lee Kang Seok e Sang Lee Hwa, del tedesco Erhard Keller e del russo Aleksander Zhekulayev, ma soprattutto quelli dei campioni olimpici e italiani Enrico Fabris e Matteo Anesi.\n\nAnesi pu\u00f2 anche essere considerato un \u00abprodotto\u00bb della pista dove si svolgeranno le gare di pattinaggio di velocit\u00e0 su ghiaccio, ovvero quella di Baselga di Pin\u00e9, dato che fin da piccolo si \u00e8 allenato proprio in questa struttura, equipaggiata di anello olimpico da 400 metri e gi\u00e0 sede di diversi appuntamenti di coppa del mondo e mondiali allround.",
				"Uomini: 500m, 1000m, 1500m, 5000m, 10000m, gara a inseguimento a squadre\nDonne: 500m, 1000m, 1500m, 3000m, 5000m, gara a inseguimento a squadre",
				"Ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere un massimo di 24 atleti, 12 uomini e 12 donne.\nPer quello che riguarda gli uomini, ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere 5 atleti per ogni distanza, ma non pi\u00f9 di quattro di essi potranno prendere parte alle distanze dei 500m, 1000m e 1500m; mentre tre di essi potranno prendere parte alle distanze dei 5000m e 10000m. Se necessario, il numero di concorrenti potrebbe essere ridotto.\nPer quello che riguarda le donne, ogni NUSF (Federazione Nazionale Sportiva Universitaria) potr\u00e0 iscrivere 5 atleti per ogni distanza, ma non pi\u00f9 di quattro di essi potranno prendere parte alle distanze dei 500m, 1000m e 1500m; mentre tre di essi potranno prendere parte alle distanze dei 3000m e 5000m. Se necessario, il numero di concorrenti potrebbe essere ridotto.\nSulle distanze dei 3000m femminili e 5000m maschili, sar\u00e0 accettato un massimo di 32 atleti per ogni evento; mentre sulle distanze dei 5000m femminili e 10000m maschili, sar\u00e0 accettato un massimo di 16 atleti per ogni evento.\n\nPer la gara a inseguimento a squadre, sar\u00e0 accettato un numero massimo di 10 squadre nazionali (uomini e donne). Ogni squadra dovr\u00e0 essere composta da quattro atleti, tre dei quali prenderanno parte alla gara. Le squadre si qualificheranno in base ai risultati del secondo miglior pattinatore della squadra, considerando il tempo fatto segnare nella gara dei 3000m per le donne e i 5000m per gli uomini.");
		x.setNomeEn("pattinaggio");
		listGps = new GPS[1];
		listGps[0] = new GPS(46.126572271845454, 11.251979685399577);
		listGps[0].setTitle("Ice Rink Piné Stadium");
		x.setGeolocations(listGps);
		db.save(x);
		x = new SportObj(
				"Biathlon",
				11,
				"Lo sport che coniuga lo sci di fondo con la precisione del tiro a segno, creando una combinazione appassionante che richiede agli atleti di gestire al meglio le forze in pista per \u00abcoprire\u00bb tutti i bersagli al poligono.\n\nUn errore pu\u00f2 costare caro: nei formati di gara pi\u00f9 comuni ad ogni errore corrisponde un giro di penalit\u00e0 di 150 metri, mentre in altri eventi pu\u00f2 arrivare addirittura a un minuto di penalit\u00e0 sul tempo finale. Per quello che riguarda lo sci di fondo, agli atleti \u00e8 richiesto di sciare in tecnica libera, lo stile sicuramente pi\u00f9 adatto per trasportare sulle spalle una carabina calibro .22 LR del peso minimo di 3,5 kg.\n\nNata come disciplina di sopravvivenza nei paesi nordici, la diffusione del biathlon \u00e8 andata in crescendo negli ultimi anni, ottenendo un successo incredibile in moltissimi paesi e conquistando i cuori di tutti gli appassionati degli sport invernali.\n\nIl debutto di questa disciplina alle Universiadi avvenne nel 1983, a Sofia in Bulgaria. In questa prima occasione, 7 paesi e 34 atleti si schierarono ai nastri di partenza e con il \u00abfucile in spalla\u00bb per prendere parte alle prime gare universitarie di biathlon. Un po\u2019 come avvenuto in altre discipline, gli atleti sovietici e dell\u2019Est Europa sono stati per molto tempo tra i migliori interpreti di questo sport, facendola da padroni per diversi anni. A partire da Harbin in Cina nel 2009, la staffetta mista entr\u00f2 a far parte del programma delle Universiadi, permettendo cos\u00ec di aumentare il numero di paesi partecipanti.\n\nMolti sono i nomi di importanti atleti che hanno avuto modo di partecipare a un\u2019Universiade prima di conquistare medaglie importanti per i rispettivi paesi: Olga Zaytseva, Thomas Sikora e Olga Nazareva sono solo alcuni esempi.\n\nDurante le Universiadi Invernali Trentino 2013 saranno ben 9 gli eventi medagliati nel programma di gara, quattro divisi per uomini e donne e una staffetta mista.\n\nLe gare di biathlon saranno organizzate sulle piste del Lago di Tesero che, dopo aver ormai raggiunto grandissima fama come sede di gare di sci di fondo, punta a conquistare i cuori degli appassionati del biathlon. Il nuovissimo poligono del Lago di Tesero e le sue piste risulteranno essere quindi un meraviglioso teatro per le gare di biathlon, andando cos\u00ec a combinarsi perfettamente nel calendario con le gare di sci nordico programmate in Val di Fiemme.",
				"Uomini: Individuale (20 km), Sprint (10 km), Inseguimento (12,5 km), Mass-start (15 km)\nDonne: Individuale (15 km), Sprint (7,5 km), Inseguimento (10 km), Mass-start (12,5 km)\nStaffetta mista 2x6 km (donne) + 2x7,5 km (uomini)",
				"Ogni NUSF (Federazione Nazionale Sportiva Universitaria) pu\u00f2 iscrivere un massimo di 16 atleti (8 uomini e 8 donne). Di questi, solo 12 (6 uomini e 6 donne) potranno prendere parte alle gare individuali e sprint.\n\nAlla gara a inseguimento potranno partecipare solo gli atleti o le atlete che termineranno la gara sprint nelle prime 60 posizioni. La partenza avviene con il vincitore dello sprint per primo e gli atleti a seguire con il distacco accumulato nello sprint.\n\nNella mass-start invece saranno disponibili solo 30 posti di partenza e quindi potranno partecipare solo i vincitori di una medaglia nelle altre gare e coloro che riusciranno a ottenere il punteggio pi\u00f9 alto seguendo il sistema di punteggio della Federazione Internazionale di Biathlon (IBU) e basandosi sui risultati delle tre gare precedenti.\n\nNella staffetta mista, ogni paese potr\u00e0 iscrivere un massimo di una squadra composta da due donne e due uomini.");
		x.setNomeEn("Biathlon");

		listGps = new GPS[1];
		listGps[0] = new GPS(46.282421123514105, 11.523825189299174);
		listGps[0].setTitle("Tesero - Lago di Tesero");
		listGps[0].setStreet("via Stazione 4");
		x.setGeolocations(listGps);

		db.save(x);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sport")
	public @ResponseBody
	List<SportObj> getAllSport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return db.findAll(SportObj.class);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sport/search")
	public @ResponseBody
	List<SportObj> searchSport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody Domanda domanda) throws IOException {

		String faq = domanda.getNome();
		faq = faq.toLowerCase();
		faq = faq.replace("%27", " ");
		faq = faq.replace("+", " ");
		faq = faq.replace("=", "");
		faq = faq.replace("%3B", " ");
		faq = faq.replace("%2C", " ");
		faq = faq.replace(".", " ");
		faq = faq.replace("%21", " ");
		faq = faq.replace("è", "�");
		faq = faq.replaceAll("\\s+$", "");
		faq = faq.replace("  ", " ");

		return getSportMatchFromDb(faq);

	}

	public List<SportObj> getSportMatchFromDb(String pattern) {
		Query query6 = new Query();
		query6.addCriteria(Criteria.where("nome").regex(pattern, "i"));

		return db.find(query6, SportObj.class);

	}

}