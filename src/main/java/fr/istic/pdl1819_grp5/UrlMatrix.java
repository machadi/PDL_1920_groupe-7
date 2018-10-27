package fr.istic.pdl1819_grp5;

import java.util.Set;

public class UrlMatrix
{

	private String link;

    private Set<FileMatrix> filesMatrix;

	public UrlMatrix(String link){
		this.link = link;
	}

	public String getLink() {
		return link;
	}

    public Set<FileMatrix> getFileMatrix() {
        return filesMatrix;
    }

    public void addFileMatrix(FileMatrix fileMatrix){
		filesMatrix.add(fileMatrix);
	}

}

