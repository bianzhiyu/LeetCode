package lc201_210;



//208. Implement Trie (Prefix Tree)
//Runtime: 85 ms, faster than 99.91% of Java online submissions for Implement Trie (Prefix Tree).
//Memory Usage: 113.2 MB, less than 5.10% of Java online submissions for Implement Trie (Prefix Tree).

/** Implement a Prefix Char Tree.
 * The root of a total tree is a virtual node, i.e. it doesn't contain a char.
 */
public class StrTrie 
{
	private boolean isEnd=false;
	private StrTrie[] son=new StrTrie[256];
	

	private void pinsert(char[]str,int p)
	{
		if (p==str.length) isEnd=true;
		else
		{
			if (son[str[p]]==null) son[str[p]]=new StrTrie();
			son[str[p]].pinsert(str, p+1);
		}
	}

	/** Inserts a word into the trie. */
	public void insert(String word) 
	{
		pinsert(word.toCharArray(),0);
	}
	
	private boolean psearch(char[] str,int p)
	{
		if (p==str.length) return isEnd;
		return son[str[p]]!=null && son[str[p]].psearch(str, p+1);
	}
	
	/** Returns if the word is in the trie. */
	public boolean search(String word) 
	{
		return psearch(word.toCharArray(),0);
	}
	
	private boolean pstw(char[] str,int p)
	{
		if (p==str.length)
		{
			if (isEnd) return true;
			for (int i=0;i<son.length;i++)
				if(son[i]!=null) return true;
			return false;
		}
		return son[str[p]]!=null && son[str[p]].pstw(str,p+1);
		
	}
	
	private int pfindShortestPrefix(char[] s)
	{
		StrTrie rt=this;
		int i=0;
		while(i<s.length)
		{
			if (rt.son[s[i]]==null) return 0;;
			if (rt.son[s[i]].isEnd) return i+1;
			rt=rt.son[s[i]];
			i++;
		}
		return 0;
	}
	
	
	/** Find a word as a prefix of s. And return this word.
	 * If there is no such word, return "".
	 * If there are more than one words as a prefix, return the one
	 * with shortest length. */
	public String findShortestPrefix(String s)
	{
		return s.substring(0,pfindShortestPrefix(s.toCharArray()));
	}
	
	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) 
	{
		return pstw(prefix.toCharArray(),0);
	}
}


