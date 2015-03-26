package structural.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositeAfter {

	public static void main(String[] args) {
		new CompositeAfter();
	}
	
	public CompositeAfter() {
		System.out.println("----Composite Design Pattern----");
		System.out.println("----After----");
		RealDir rRoot = new RealDir("Design Pattern");
		RealDir rSubDir = new RealDir("Structural Pattern");
		RealFile rFile = new RealFile("Design Pattern.doc");
		RealFile rSubFile = new RealFile("Composite Pattern.doc");
		
		rRoot.addItem(rFile);
		rRoot.addItem(rSubDir);
		rSubDir.addItem(rSubFile);
		
		rRoot.ls();
	}
	
	abstract class AbstractFile {
		protected String name;
		protected String space = "    ";

		public AbstractFile(String name) {
			this.name = name;
		}
		
		public abstract void ls();
		
		public void addSpace(String space) {
			this.space += space;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getSpace() {
			return space;
		}
		
		public void setSpace(String space) {
			this.space = space;
		}
		
	}
	
	class RealDir extends AbstractFile {

		private List<AbstractFile> list = new ArrayList<>();
		
		public RealDir(String name) {
			super(name);
		}
		
		@Override
		public void ls() {
			System.out.println(this.space + this.name);
			
			for (AbstractFile file : list) {
				file.ls();
			}
		}
		
		public void addItem(AbstractFile file) {
			file.addSpace(this.space);
			list.add(file);
		}

	}
	
	class RealFile extends AbstractFile {
		
		public RealFile(String name) {
			super(name);
		}

		@Override
		public void ls() {
			System.out.println(this.space + this.name);
		}

	}
}
