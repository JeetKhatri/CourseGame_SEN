using System.Collections.Generic;

[System.Serializable]
public class QuestionList
{

    public string createdBy;
    public string name;
    public string endtime;
    public string starttime;
    public string responseStatus;
    public List<Question> quizcontent;
}
