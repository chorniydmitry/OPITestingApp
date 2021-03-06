package ru.fssprus.r82.entity;

/**
 * @author Chernyj Dmitry
 *
 */
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ru.fssprus.r82.utils.AppConstants;

@Entity
@Table(name = "question")
public class Question extends Model {
	@NotNull(message = AppConstants.VALID_QUEST_QUESTIONSET_NOTNULL)
	@ManyToOne
	@JoinColumn(name = "questionset_id")
	private QuestionSet questionset;

	@NotNull(message = AppConstants.VALID_QUEST_NAME_NOTNULL)
	@Size(min = AppConstants.QUESTION_TEXT_MIN_LENGTH, message = AppConstants.VALID_QUEST_NAME_SIZE)
	@Column(name = "title", length = 2096)
	private String title;

	@Size(min = AppConstants.MIN_ANSWERS_AMOUNT, max = AppConstants.MAX_ANSWERS_AMOUNT, message = AppConstants.VALID_QUEST_ANSWERS_AMOUNT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
	private Set<Answer> answers;
	
	@Column(name = "image_link")
	private String imageLink;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public QuestionSet getQuestionSet() {
		return questionset;
	}

	public void setQuestionSet(QuestionSet questionSet) {
		this.questionset = questionSet;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + ((questionset == null) ? 0 : questionset.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (imageLink == null) {
			if (other.imageLink != null)
				return false;
		} else if (!imageLink.equals(other.imageLink))
			return false;
		if (questionset == null) {
			if (other.questionset != null)
				return false;
		} else if (!questionset.equals(other.questionset))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [title=" + title + ", answers=" + answers + ", imageLink=" + imageLink + "]";
	}
	
	
}
