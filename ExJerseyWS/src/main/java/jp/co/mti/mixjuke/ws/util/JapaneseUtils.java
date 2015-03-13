package jp.co.mti.mixjuke.ws.util;

public class JapaneseUtils
{
	public static final int READING_TITLE = 0;
	public static final int READING_ALBUM = 1;
	public static final int READING_ARTIST = 2;

	private static char[][] FullToHalfMap = {
			{ 0xFF61, 0x3002 },
			{ 0xFF62, 0x300C },
			{ 0xFF63, 0x300D },
			{ 0xFF64, 0x3001 },
			{ 0xFF65, 0x30FB },
			{ 0xFF66, 0x30F2 },
			{ 0xFF67, 0x30A1 },
			{ 0xFF68, 0x30A3 },
			{ 0xFF69, 0x30A5 },
			{ 0xFF6A, 0x30A7 },
			{ 0xFF6B, 0x30A9 },
			{ 0xFF6C, 0x30E3 },
			{ 0xFF6D, 0x30E5 },
			{ 0xFF6E, 0x30E7 },
			{ 0xFF6F, 0x30C3 },
			{ 0xFF70, 0x30FC },
			{ 0xFF71, 0x30A2 },
			{ 0xFF72, 0x30A4 },
			{ 0xFF73, 0x30A6 },
			{ 0xFF74, 0x30A8 },
			{ 0xFF75, 0x30AA },
			{ 0xFF76, 0x30AB },
			{ 0xFF77, 0x30AD },
			{ 0xFF78, 0x30AF },
			{ 0xFF79, 0x30B1 },
			{ 0xFF7A, 0x30B3 },
			{ 0xFF7B, 0x30B5 },
			{ 0xFF7C, 0x30B7 },
			{ 0xFF7D, 0x30B9 },
			{ 0xFF7E, 0x30BB },
			{ 0xFF7F, 0x30BD },
			{ 0xFF80, 0x30BF },
			{ 0xFF81, 0x30C1 },
			{ 0xFF82, 0x30C4 },
			{ 0xFF83, 0x30C6 },
			{ 0xFF84, 0x30C8 },
			{ 0xFF85, 0x30CA },
			{ 0xFF86, 0x30CB },
			{ 0xFF87, 0x30CC },
			{ 0xFF88, 0x30CD },
			{ 0xFF89, 0x30CE },
			{ 0xFF8A, 0x30CF },
			{ 0xFF8B, 0x30D2 },
			{ 0xFF8C, 0x30D5 },
			{ 0xFF8D, 0x30D8 },
			{ 0xFF8E, 0x30DB },
			{ 0xFF8F, 0x30DE },
			{ 0xFF90, 0x30DF },
			{ 0xFF91, 0x30E0 },
			{ 0xFF92, 0x30E1 },
			{ 0xFF93, 0x30E2 },
			{ 0xFF94, 0x30E4 },
			{ 0xFF95, 0x30E6 },
			{ 0xFF96, 0x30E8 },
			{ 0xFF97, 0x30E9 },
			{ 0xFF98, 0x30EA },
			{ 0xFF99, 0x30EB },
			{ 0xFF9A, 0x30EC },
			{ 0xFF9B, 0x30ED },
			{ 0xFF9C, 0x30EF },
			{ 0xFF9D, 0x30F3 },
			{ 0xFF9E, 0x3099 }, // voiced sound mark
			{ 0xFF9F, 0x309A }, // semi-voiced sound mark
			{ 0, 0 }
	};

	private static char[][] FullDakutenMap = {
			{ 0x30AC, 0x30AB },
			{ 0x30AE, 0x30AD },
			{ 0x30B0, 0x30AF },
			{ 0x30B2, 0x30B1 },
			{ 0x30B4, 0x30B3 },
			{ 0x30B6, 0x30B5 },
			{ 0x30B8, 0x30B7 },
			{ 0x30BA, 0x30B9 },
			{ 0x30BC, 0x30BB },
			{ 0x30BE, 0x30BD },
			{ 0x30C0, 0x30BF },
			{ 0x30C2, 0x30C1 },
			{ 0x30C5, 0x30C4 },
			{ 0x30C7, 0x30C6 },
			{ 0x30C9, 0x30C8 },
			{ 0x30D0, 0x30CF },
			{ 0x30D3, 0x30D2 },
			{ 0x30D6, 0x30D5 },
			{ 0x30D9, 0x30D8 },
			{ 0x30DC, 0x30DB },
			{ 0x30F4, 0x30A6 },
			{ 0x30F7, 0x30EF },
			{ 0x30F8, 0x30F0 },
			{ 0x30F9, 0x30F1 },
			{ 0x30FA, 0x30F2 },
			{ 0, 0 }
	};

	private static char[][] FullHandakutenMap = {
			{ 0x30D1, 0x30CF },
			{ 0x30D4, 0x30D2 },
			{ 0x30D7, 0x30D5 },
			{ 0x30DA, 0x30D8 },
			{ 0x30DD, 0x30DB },
			{ 0, 0 }
	};

	private static char GetMap(char[][] map, char c, boolean reverse)
	{
		for (int i = 0; map[i][0] != 0 && map[i][1] != 0; ++i)
		{
			if (reverse && map[i][0] == c)
				return map[i][1];
			if (!reverse && map[i][1] == c)
				return map[i][0];
		}

		return 0;
	}

	private static char GetHalfChar(char c)
	{
		if (c < 0xff01 || c > 0xff5e)
			return 0;

		return (char) (c - 0xff01 + 0x0021);
	}

	private static char GetFullChar(char c)
	{
		if (c < 0x0021 || c > 0x007c)
			return 0;

		return (char) (c - 0x0021 + 0xff01);
	}

	public static String toHalfWidth(String text)
	{
		if (text == null)
		{
			return null;
		}

		StringBuffer out = new StringBuffer();

		for (int i = 0; i < text.length(); ++i)
		{
			char c = text.charAt(i);
			if (c < 0x3000 || c > 0x30ff)
				out.append(c);
			else
			{
				char x;
				char y = 0;
				char add = 0;
				if ((x = GetMap(FullDakutenMap, c, true)) != 0)
				{
					c = x;
					add = 0xff9e;
				}
				else if ((x = GetMap(FullHandakutenMap, c, true)) != 0)
				{
					c = x;
					add = 0xff9f;
				}
				if ((x = GetHalfChar(c)) != 0)
					y = x;
				else if ((x = GetMap(FullToHalfMap, c, false)) != 0)
					y = x;
				if (y != 0)
				{
					out.append(y);
				}
				if (add != 0)
					out.append(add);
			}
		}

		return out.toString();
	}

	public static String toFullWidth(String text)
	{
		return toFullWidth(text, true, true);
	}

	public static String toFullWidthKatakana(String text)
	{
		return toFullWidth(text, false, true);
	}

	public static String normalizeWidths(String text)
	{
		return toFullWidth(text, true, false);
	}

	public static String toFullWidth(String text, boolean convertLatin, boolean fullWidthLatin)
	{
		if (text == null)
		{
			return null;
		}

		StringBuffer out = new StringBuffer();

		for (int i = 0; i < text.length(); ++i)
		{
			char c = text.charAt(i);
			char x = 0;

			if (convertLatin && fullWidthLatin && (x = GetFullChar(c)) != 0)
				c = x;
			else if (convertLatin && !fullWidthLatin && (x = GetHalfChar(c)) != 0)
				c = x;
			else if (c >= 0xff66 && c <= 0xff9f)
			{
				if ((x = GetMap(FullToHalfMap, c, true)) != 0)
					c = x;
				if (i < text.length() - 1 && ((x = GetMap(FullDakutenMap, c, false))) != 0)
				{
					char nextc = text.charAt(i + 1);
					if (nextc == 0xff9e)
						c = x;
				}
				if (i < text.length() - 1 && ((x = GetMap(FullHandakutenMap, c, false))) != 0)
				{
					char nextc = text.charAt(i + 1);
					if (nextc == 0xff9f)
						c = x;
				}
			}
			if (c != 0xff9e && c != 0xff9f && c != 0x3099 && c != 0x309a)
				out.append(c);
		}

		return out.toString();
	}

	public static boolean isRomaji(char c)
	{
		return (c >= 0xff21 && c <= 0xff3a) || (c >= 0xff41 && c <= 0xff5a);
	}

	public static String toHiragana(String text)
	{
		if (text == null)
		{
			return null;
		}

		StringBuffer out = new StringBuffer(text.length());

		for (int i = 0; i < text.length(); ++i)
		{
			char c = text.charAt(i);
			if (c >= 0x30a1 && c <= 0x30f6)
				c = (char) (c - 0x30a1 + 0x3041);
			out.append(c);
		}

		return out.toString();
	}

	public static String toKatakana(String text)
	{
		if (text == null)
		{
			return null;
		}

		StringBuffer out = new StringBuffer(text.length());

		for (int i = 0; i < text.length(); ++i)
		{
			char c = text.charAt(i);
			if (c >= 0x3041 && c <= 0x3096)
				c = (char) (c - 0x3041 + 0x30a1);
			out.append(c);
		}

		return out.toString();
	}

	public static boolean hasJapanese(String text)
	{
		for (int i = 0; i < text.length(); ++i)
		{
			char c = text.charAt(i);

			if ((c >= 0x30a0 && c <= 0x30ff) || // katakana
					(c >= 0x31f0 && c <= 0x31ff) || // katakana phonetic extensions
					(c >= 0x3040 && c <= 0x309f) || // hiragana
					(c >= 0x3190 && c <= 0x319f) || // kanbun
					(c >= 0x2e80 && c <= 0x31ef) || // unified cjk support blocks
					(c >= 0x3400 && c <= 0x4dbf) || // cjk unihan extension A
					(c >= 0x4e00 && c <= 0x9fff) || // cjk unihan
					(c >= 0x20000 && c <= 0x2a6df) || // cjk unihan extension B
					(c >= 0xff00 && c <= 0xffef)) // halfwidth and fullwidth forms
				return true;
		}

		return false;
	}

	public static boolean isLatin(char c)
	{
		if ((c >= 'a' && c <= 'z') ||
				(c >= 'A' && c <= 'Z') ||
				(c >= '0' && c <= '9') ||
				GetHalfChar(c) != 0)
			return true;
		return false;
	}

	public static int getSorting(String text)
	{
		if (text == null || text.length() == 0)
		{
			return 0;
		}

		char c = text.charAt(0);

		if ((c >= '\u30a0' && c <= '\u30ff') || // katakana
				(c >= '\u31f0' && c <= '\u31ff')) // katakana phonetic extensions
			return 10;
		else if (c >= '\u3040' && c <= '\u309f') // hiragana
			return 10;
		else if ((c >= 'a' && c <= 'z') ||
				(c >= 'A' && c <= 'Z')) // ascii alphabet
			return 20;
		else if ((c >= '0' && c <= '9')) // ascii numbers
			return 30;
		else if ((c >= '\u3190' && c <= '\u319f') || // kanbun
				(c >= '\u2e80' && c <= '\u31ef') || // unified cjk support blocks
				(c >= '\u3400' && c <= '\u4dbf') || // cjk unihan extension A
				(c >= '\u4e00' && c <= '\u9fff')) // cjk unihan
//					 (c >= '\u20000' && c <= '\u2a6df')) // cjk unihan extension B
			return 30;
		else if (c >= '\uff00' && c <= '\uffef')
		{ // halfwidth and fullwidth forms
			if ((c >= '\uff21' && c <= '\uff3a') ||
					(c >= '\uff41' && c <= '\uff5a')) // fullwidth A-Z a-z
				return 20;
			else if (c >= '\uff10' && c <= '\uff19') // fullwidth 0-9
				return 30;
			else if (c >= '\uff66' && c <= '\uff9d') // halfwidth katakana
				return 10;
		}

		return 40;
	}

	public static String processReading(String reading, int type)
	{
		return processReading(null, reading, type);
	}

	public static String processReading(String display, String reading, int type)
	{
		return processReading(display, reading);
	}

	private static String processReading(String display, String yomigana)
	{
		if ((display == null || display.length() == 0) && (yomigana == null || yomigana.length() == 0))
		{
			return null;
		}

		if (display != null && display.length() > 0 && isLatin(display.charAt(0)))
		{
			yomigana = display;
		}
		else if (yomigana == null || yomigana.equals("null"))
		{
			yomigana = display;
		}

		if (yomigana != null)
		{
			yomigana = yomigana.trim();
		}

		if (yomigana == null || yomigana.length() == 0)
		{
			yomigana = display;
		}

		yomigana = normalizeWidths(toKatakana(yomigana));

		if (yomigana.toLowerCase().startsWith("the ") && yomigana.length() > 5)
		{
			yomigana = yomigana.substring(4);
		}

		return yomigana;
	}
}
