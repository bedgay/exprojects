package structural.composite;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CompositeBefore {
	
	public static void main(String[] args) {
		new CompositeBefore();
	}
	
	public CompositeBefore() {
		System.out.println("----Composite Design Pattern----");
		System.out.println("----Before----");
		LegacyDir root = new LegacyDir("Design Pattern");
		LegacyDir subDir = new LegacyDir("Structural Pattern");
		LegacyFile file = new LegacyFile("Design Pattern.doc");
		LegacyFile subFile = new LegacyFile("Composite Pattern.doc");
		
		root.addItem(file);
		root.addItem(subDir);
		subDir.addItem(subFile);
		
		root.ls();
	}
	
	class LegacyDir {
		
		private String space = "    ";
		private String name;
		private List list = new ArrayList();
		
		public LegacyDir(String name) {
			this.name = name;
		}
		
		public void ls() {
			System.out.println(space + name);
			for (Object obj : list) {
				if (obj instanceof LegacyDir) {
					LegacyDir dir = (LegacyDir) obj;
					dir.ls();
				} else if (obj instanceof LegacyFile) {
					LegacyFile file = (LegacyFile) obj;
					file.ls();
				}
			}
		}
		
		public void addSpace(String space) {
			this.space += space;
		}
		
		public void addItem(Object obj) {
			if (obj instanceof LegacyDir) {
				LegacyDir dir = (LegacyDir) obj;
				dir.addSpace(space);
				list.add(dir);
			} else if (obj instanceof LegacyFile) {
				LegacyFile file = (LegacyFile) obj;
				file.addSpace(space);
				list.add(file);
			}
		}

		public String getSpace() {
			return space;
		}

		public void setSpace(String space) {
			this.space = space;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
	
	class LegacyFile {
		
		private String space = "    ";
		private String name;
		
		public LegacyFile(String name) {
			this.name = name;
		}
		
		public void ls() {
			System.out.println(space + name);
		}
		
		public void addSpace(String space) {
			this.space += space;
		}

		public String getSpace() {
			return space;
		}

		public void setSpace(String space) {
			this.space = space;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
