package osmProjekt2;

import java.io.Serializable;
import java.util.Date;

public class Exam implements GetSetId, Serializable {

	private static final long serialVersionUID = 200906271625L;

	private Date exam_date;
	private int pulse_value;
	private int pressure_value;
	
	transient private int id;
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	Date getDate()
	{
		return exam_date;
	}
	
	int getPulse()
	{
		return pulse_value;
	}
	
	void setDate(Date date)
	{
		this.exam_date = date;
	}
	
	void setPulse(int pulse_value)
	{
		this.pulse_value = pulse_value;
	}
	
	void setPressure(int pressure_value)
	{
		this.pressure_value = pressure_value;
	}
	
	

}
