package com.dub.spring.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "director")
public class Director implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected String firstName;
	protected String lastName;
	protected long id;
	
	protected Date birthDate;
	
	protected Set<Movie> movie;
	
    public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directorId")
    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName()
    {
        return this.firstName;
    }
    
    @Basic
    @Column(name = "lastName")
    public String getLastName()
    {
        return this.lastName;
    }

    
    @Temporal(TemporalType.DATE)
    @Column(name = "birthDate")
    public Date getBirthDate()
    {
        return this.birthDate;
    }


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
	public Set<Movie> getMovie() {
		return movie;
	}

	public void setMovie(Set<Movie> movie) {
		this.movie = movie;
	}	
}
