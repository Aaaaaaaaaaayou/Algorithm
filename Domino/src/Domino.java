import java.util.Arrays;
public class Domino {
	public class Unit{
		int L;//��¼��ߵ���
		int R;//��¼�ұߵ���
		int W=0;//��¼״̬
		int[] M = new int[2];//M[0]��¼��Ԫ������ʱ�����ͣ�M[1]��¼��Ԫ�ص���ʱ������
		int[] P = new int[2];//P[0]��¼��Ԫ������ʱ������ʱ����һԪ�ص�W״̬��P[1]��¼��Ԫ�ص��������ʱ����һԪ�ص�W״̬
		public Unit(int L,int R){
			this.L = L;
			this.R = R;
		}
	}
	private int n;
	private int compNum;
	private int maxSum;
	Unit arr[]=null;
	//��ʼ������
	public void initData(Unit[] arr)
	{
		this.n = arr.length;
		this.arr =  arr;
	}
	//������
	public void show()
	{
		System.out.println("����Ϊ��"+this.maxSum);
		System.out.println("����ŵ����״̬:");
		this.dominoStatus();
	}
	//����ŵ����״̬
	private void dominoStatus(){
		for(int i=0;i<this.n;i++)
		{
			int min = (this.arr[i].L<this.arr[i].R)?this.arr[i].L:this.arr[i].R;
			int max = (this.arr[i].L>this.arr[i].R)?this.arr[i].L:this.arr[i].R;
			int left;
			int right;
			if(this.arr[i].W == 1){
				left = min;
				right = max;
			}else{
				left = max;
				right = min;
			}
			System.out.print(left+"|"+right+"\t");
		}
		System.out.print("\n");
	}
	public void maxSum(){
		Arrays.fill(this.arr[0].M,0);//��һ��Ԫ�ص�M��Ϊ0
		Arrays.fill(this.arr[0].P,0);//��һ��Ԫ�ص�P��Ϊ0
		for(int i=1;i<=this.n-1;i++){
			int ai_1 = (this.arr[i-1].L<this.arr[i-1].R)?this.arr[i-1].L:this.arr[i-1].R;
			int bi_1 = (this.arr[i-1].L>this.arr[i-1].R)?this.arr[i-1].L:this.arr[i-1].R;
			int ai = (this.arr[i].L<this.arr[i].R)?this.arr[i].L:this.arr[i].R;
			int bi = (this.arr[i].L>this.arr[i].R)?this.arr[i].L:this.arr[i].R;
			//��M[0]
			if(this.arr[i-1].M[0]+bi_1*ai>this.arr[i-1].M[1]+ai_1*ai){
				this.arr[i].M[0] = this.arr[i-1].M[0]+bi_1*ai;
				this.arr[i].P[0] = 1;
			}else{
				this.arr[i].M[0] = this.arr[i-1].M[1]+ai_1*ai;
				this.arr[i].P[0] = -1;
			}
			//��M[1]
			if(this.arr[i-1].M[0]+bi_1*bi>this.arr[i-1].M[1]+ai_1*bi){
				this.arr[i].M[1] = this.arr[i-1].M[0]+bi_1*bi;
				this.arr[i].P[1] = 1;
			}else{
				this.arr[i].M[1] = this.arr[i-1].M[1]+ai_1*bi;
				this.arr[i].P[1] = -1;
			}
		}
		//�����ֵ
		if(this.arr[this.n-1].M[1]>this.arr[this.n-1].M[0]){
			this.maxSum = this.arr[this.n-1].M[1];
			this.arr[this.n-1].W = -1;
		}else{
			this.maxSum = this.arr[this.n-1].M[0];
			this.arr[this.n-1].W = 1;
		}
		//����ǰ���Ԫ�ص��������
		for(int i=this.n-1;i>=1;i--){
			this.arr[i-1].W = (this.arr[i].W == 1)?this.arr[i].P[0]:this.arr[i].P[1];
		}
	}
	public static void main(String args[]){
		Domino domino = new Domino();
		Unit[] arr = new Unit[6];
		arr[0] = domino.new Unit(5,8);//��������÷�
		arr[1] = domino.new Unit(4,2);
		arr[2] = domino.new Unit(9,6);
		arr[3] = domino.new Unit(7,7);
		arr[4] = domino.new Unit(3,9);
		arr[5] = domino.new Unit(11,10);
		domino.initData(arr);
		domino.maxSum();
		domino.show();		
	}
}
