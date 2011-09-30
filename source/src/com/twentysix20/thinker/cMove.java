package com.twentysix20.thinker;

public class cMove extends Command
{
	public void execute(Pheno p, byte x, byte y)
	{
		byte temp;
		if (x >= 128)
			temp = p.getRegister((byte)(x & 127));
		else
			temp = p.getBoard(x);

		if (y >= 128)
			p.setRegister((byte)(y & 127),temp);
		else
			p.setBoard(y,temp);
		p.incRunPtr();
	}

	public static void main(String[] Args) throws Exception
	{
		System.out.println("action is abstract.\n");
	}
}