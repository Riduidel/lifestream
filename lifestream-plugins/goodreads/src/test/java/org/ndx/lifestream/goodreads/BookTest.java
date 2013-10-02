package org.ndx.lifestream.goodreads;

import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Map;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Before;
import org.junit.Test;

public class BookTest {
	private Goodreads tested;

	@Before
	public void loadCredentials() {
		tested = new Goodreads();
	}



	@Test
	public void canCreateAValidBookObjectFromAnUnreadBook() {
		String headers = "Book Id, Title, Author, Author l-f, Additional Authors, ISBN, ISBN13, My Rating, Average Rating, Publisher, Binding, Number of Pages, Year Published, Original Publication Year, Date Read, Date Added, Bookshelves, Bookshelves with positions, Exclusive Shelf, My Review, Spoiler, Private Notes, Read Count, Recommended For, Recommended By, Owned Copies, Original Purchase Date, Original Purchase Location, Condition, Condition Description, BCID";
		String[] headersArray = headers.split(", ");
		String moorcock = "Michael Moorcock";
		String gilliam = "Richard Gilliam";
		String kramer = "Edward F. Kramer";
		String[] unreadBook = new String[] { "2387115", "Contes du Loup Blanc : Par-delà le multivers", moorcock, "Moorcock, Michael",
						gilliam +", "+kramer, "=\"2266071254", "=\"9782266071253", "0", "3.97", "Pocket", "Mass Market Paperback", "253",
						"1996", "1977", "", "2009/06/28", "to-read, rayon-fantasy-et-sf", "to-read (#25), rayon-fantasy-et-sf (#385)", "to-read", "", "", "",
						"", "", "", "0", "", "", "", "", "" };
		Map<String, Integer> headersMap = tested.getColumnsNamesToColumnsIndices(headersArray);
		assertThat(unreadBook.length, Is.is(headersArray.length));
		Book unread = tested.createBook(headersMap, unreadBook);
		assertThat(unread.getAuthors(), IsCollectionContaining.hasItems(moorcock, gilliam, kramer));
		assertThat(unread.tags.contains("to-read"), Is.is(true));
	}

	@Test
	public void canCreateAvalidBookObjectFromAReadBook() {
		String headers = "Book Id, Title, Author, Author l-f, Additional Authors, ISBN, ISBN13, "
						+ "My Rating, Average Rating, Publisher, Binding, Number of Pages, Year Published, "
						+ "Original Publication Year, Date Read, Date Added, Bookshelves, Bookshelves with positions, "
						+ "Exclusive Shelf, My Review, Spoiler, Private Notes, Read Count, Recommended For, Recommended By, "
						+ "Owned Copies, Original Purchase Date, Original Purchase Location, Condition, Condition Description, BCID";
		String[] headersArray = headers.split(", ");
		String[] readBook = new String[] {
						"383380",
						"Schismatrice +",
						"Bruce Sterling",
						"Sterling, Bruce",
						"Jean Bonnefoy, William Desmond",
						"=\"207042331X",
						"=\"9782070423316",
						"5",
						"3.95",
						"",
						"",
						"",
						"",
						"1996",
						"2008/05/13",
						"2008/05/12",
						"cyberpunk, dystopie, humanité, immortalité, mémoire, near-space, politique, post-humanité, voyage, top, rayon-fantasy-et-sf",
						"cyberpunk (#18), dystopie (#10), humanité (#16), immortalité (#11), mémoire (#10), near-space (#9), politique (#15), post-humanité (#2), voyage (#28), top (#9), rayon-fantasy-et-sf (#411)",
						"read",
						"J'avais lu ce livre il y a déja quelques années, et en avait étéé positivement émerveillé. Car après la déferlante du cyberpunk, [author:Sterling] nous revenait avec une oeuvre aux dimensions épiques, embrasssant dans sa fresque futuriste de très nombreuses visions de l'humanité et une rencontre avec les extra-terrestres.<br/><br/>J'ai cette fois-ci été un peu plus touché par le côté humain du personnage principal (auquel on ne peut décement pas donner le titre de héros, puisqu'il passe l'essentiel du roman à fuir : sa femme, ses anciens amis, ses responsabilités, l'humanité). Toutefois, à cause d'une éducation étrangement écartelée, sa fuite n'est pas réellement éperdue, mais massquée la plupart du temps derrière des motifzs nobles. Et ce sont ces motifs qui vont nous permettre de visiter les endroits les plus interlopes que cette humanité spatiale a conquis : des stations spatiales quasiment abandonnées, des centres diplomatiques et commerciaux de contact avec les extra-terrestres, et enfin le centre de terraformation de Mars.<br/><br/>Et à chaque fois, comme lors de la première lecture, j'ai été ébahi devant le sens de la mise en scène dont sait faire preuve l'auteur pour nous rendre des décors tour à tour inquiétants, merveilleux, totallement invivables, ou tout simplement dangereux.<br/><br/>Un autre point intéressant dans ce roman, c'est la philosophie évoquée par ce personnage. Car même si les objectifs qu'il défend évoluent (mais quels objectifs n'évolueraient pas au cours dde périodes de vies de plusieurs siècles - en passant, c'est un point commun avec [book:Les menhirs de glace], qui montre néanmoins plus en détail les limites de la mémoire - qui sont néanmoins évoqués ici avec ce que j'appelerais de la finesse, à défaut d'un terme plus approprié), il garde tout au long de sa vie une confiance claire en les capacités d'une bonne négociation à éviter tous les conflits, confiance qui sera régulièrement trahie par d'autres humains qui ont moins foi que lui en les capacités de la diplomatie. Peut-être que, dans ce cas, l'un des postulats de [author:Sterling] a été de donner corps à la phrase célèbre qui dit que la guerre est la continuation de la diplomatie par d'autres moyens, mais j'en doute.<br/><br/>Je parlais du côté humain du livre en préambule, mais je m'aperçois que je ne parle que du personnage principal. Or tous sont traités avec la même finesse, qui leur permet d'exisster avec une réelle force. C'est ainsi le cas des différents personnages secondaires, mais aussi de l'humanité dans son ensemble, qui garde dans l'espace toutes ses bassesses, comme les luttes d'influence, mais aussi toutes ses grandeurs, comme la capacité à créer et à imaginer toujours un monde meilleur.<br/><br/>Tout ça fait de ce rtoman une oeuvre incroyablement clairvoyante, je trouve, même si elle présente certains défaults, comme par exemple un certain manque d'unité.<br/><br/>Peut-être que ce manque d'unité est dû à nos limites d'humains actuels, incapables d'embrasser des espérances de vies se comptant en siècles, mais je crois plutôt que c'est la variété des lieux dans lesquels l'auteur promène son personnage principal qui m'a donné cette impression.<br/><br/>Notez bien qu'il s'agit d'un défaut minime, rendu encore plus mineur d'ailleurs par les nouvelles suivant le roman, qui ouvrent de nouveaux aperçus sur l'univers autour du héros, même s'il s'agit plutôt de l'univers à la fin du roman, avec une planète Mars quasiment colonisée.<br/><br/>Enfin, dans tous les cas j'ai beaucoup aimé cette oeuvre qui, en mon sens, apporte beaucoup de fraîcheur au space-opera en en étant un, par le décor, sans en être un, par le manque d'action, rempalcé ici par une vue beaucoup plus contemplative de ce décor, au demeurant magnifique.",
						"", "", "", "", "", "1", "", "", "unspecified", "", "" };
		Map<String, Integer> headersMap = tested.getColumnsNamesToColumnsIndices(headersArray);
		assertThat(readBook.length, Is.is(headersArray.length));
		Book read = tested.createBook(headersMap, readBook);
		assertThat(read.tags.contains("read"), Is.is(true));
	}
}
