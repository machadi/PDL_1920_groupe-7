package fr.istic.pdl1819_grp5;

import java.util.Set;

public class UrlMatrix
{

	private String link;

	private UrlType urlType;

    private Set<FileMatrix> fileMatrix;

	public UrlMatrix(String link, UrlType urlType){
		this.link = link;
		this.urlType = urlType;
	}

	public String getLink() {
		// TODO implement me
		return "";
	}

    public Set<FileMatrix> getFileMatrix() {
        return fileMatrix;
    }

}

