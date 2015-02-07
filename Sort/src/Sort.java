
public class Sort {
	private int n;
	private int compNum;
	private int arr[]=null;
	//��ʼ������
	public void InitData(int[] arr)
	{
		this.n = arr.length;
		this.arr =  arr;
		//for(int i=0;i<arr.length;i++)
		//System.out.println(this.arr[i]);
	}
		//��������
		void InsertSort()
		{
			this.compNum = 0;
			for(int j=1;j<this.n;j++)
			{
				int key = this.arr[j];
				int i = j-1;
				while((this.compNum++>=0) && (i>=0) && (this.arr[i]>key))
				{				
					arr[i+1] = arr[i];
					i = i-1;
				}
				arr[i+1] = key;
			}
		}
		//��������,���ϵ�
		void DCSort()
		{
			this.compNum=0;
			Divide(arr,0,n-1);
		}
		void Divide(int[] arr,int p,int r)
		{
			if(p<r)
			{
				int q=(p+r)/2;
				Divide(arr,p,q);
				Divide(arr,q+1,r);
				Merge(arr,p,q,r);
			}
		}
		void Merge(int[] arr,int p,int q,int r)
		{
			int n1 = q-p+1;
			int n2 = r-q;
			int[] L = new int[n1+1];
			int[] R = new int[n2+1];
			for(int i=0;i<n1;i++)
			{
				L[i] = arr[p+i];
			}
			for(int j=0;j<n2;j++)
			{
				R[j] = arr[q+1+j];
			}
			L[n1]=9999;//��һ������ֵ
			R[n2]=9999;//��һ������ֵ
			int i=0;
			int j=0;
			for(int k=p;k<=r;k++)
			{
				this.compNum++;
				if(L[i]<=R[j])
				{
					arr[k] = L[i++];
				}else{
					arr[k] = R[j++];
				}
			}

		}
		//��������,�ҵ�
		void DCSortMy()
		{
			this.compNum=0;
			DivideMy(arr,0,n-1);
		}
		void DivideMy(int[] arr,int p,int r)
		{
			if(p<r)
			{
				int q=(p+r)/2;
				DivideMy(arr,p,q);
				DivideMy(arr,q+1,r);
				MergeMy(arr,p,q,r);
			}
		}
		void MergeMy(int[] arr,int p,int q,int r)
		{
			int n1 = q-p+1;
			int n2 = r-q;
			int[] L = new int[n1];//��ͬ��
			int[] R = new int[n2];
			for(int i=0;i<n1;i++)
			{
				L[i] = arr[p+i];
			}
			for(int j=0;j<n2;j++)
			{
				R[j] = arr[q+1+j];
			}
			//L[n1]=9999;//��һ������ֵ
			//R[n2]=9999;//��һ������ֵ
			int i=0;
			int j=0;
			for(int k=p;k<=r;k++)
			{

				if((this.compNum++>0)&&L[i]<=R[j])
				{
					arr[k] = L[i++];
				}else{
					arr[k] = R[j++];
				}
				
				if((this.compNum++>0)&&i==n1)
				{
					for(int l=k+1;l<=r;l++)
					{
						arr[l] = R[j++];
					}
					break;
				}
			
				if((this.compNum++>0)&&j==n2)
				{
					for(int l=k+1;l<=r;l++)
					{
						arr[l] = L[i++];
					}
					break;
				}
			}

		}
		//////////////������ʼ//////////
		void HeapSort()
		{
			this.compNum = 0;
			int heapsize = this.n;
			//�����
			BuildMaxHeap(this.arr,heapsize);
			for(int i=this.n-1;i>=1;i--)
			{
				int tmp;
				tmp = this.arr[i];
				this.arr[i] = this.arr[0];
				this.arr[0] = tmp;
				heapsize -= 1;
				MaxHeapify(this.arr,0,heapsize);
			}
		}
		void BuildMaxHeap(int[] arr,int heapsize)
		{
			for(int i=heapsize/2-1;i>=0;i--)
			{
				MaxHeapify(arr,i,heapsize);
			}
		}
		void MaxHeapify(int[] arr,int i,int heapsize)
		{
			int left=-1;
			int right=-1;
			int ileft = Left(i);
			int iright = Right(i);
			if (ileft < heapsize)
				left = arr[ileft];
			if (iright < heapsize)
				right = arr[iright];
			int k;
			//�����ӽڵ�Ƚ�
			if((this.compNum++>0) && arr[i] < left && ileft < heapsize)
			{
				
				k = ileft;
			}else{
				k = i;
			}
			//�����ӽڵ�Ƚ�
			if((this.compNum++>0) && arr[k] < right && iright < heapsize)
			{

				k = iright;
			}
			if(k!=i)
			{
				int tmp = arr[i];
				arr[i] = arr[k];
				arr[k] = tmp;
				
				MaxHeapify(arr,k,heapsize);
			}

		}
		int Left(int i)
		{
			return i*2+1;
		}
		int Right(int i)
		{
			return (i*2+2);
		}
		//////////////���������//////////
		
		/*
		 * ��������
		 */
		void QuickSort()
		{
			this.compNum=0;
			QSort(this.arr,0,this.n-1);
		}
		void QSort(int[] arr,int p,int r)
		{
			if(p < r)
			{
				int q = Partition(arr,p,r);
				QSort(arr,p,q-1);
				QSort(arr,q+1,r);
			}
		}
		int Partition(int[] arr,int p,int r){
			int x = arr[r];
			int i = p-1;
			int j;
			for(j=p;j<=r;j++){
				if((this.compNum++>0)&&arr[j]<=x){
					i++;
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;				
				}
			}
			int tmp = arr[r];
			arr[r] = arr[i+1];
			arr[i+1] = tmp;
			return i+1;
		}
		/**************�����������********/
		//����������
		void Show()
		{
			System.out.println("���������");
			for(int i=0;i<this.n;i++)
			{
				System.out.println(this.arr[i]);
			}
		}
	 public static void main(String args[]) {
		 //������
		 System.out.println("������");
		 int arr[] = {8,7,6,5,4,3,2,1};
		 Sort sort= new Sort();	 
		 sort.InitData(arr);
		 sort.HeapSort();
		 sort.Show();
		 System.out.println("�Ƚϴ���");
		 System.out.println(sort.compNum);
		 //�鲢����
		 System.out.println("�鲢����");
		 int arr2[] = {8,7,6,5,4,3,2,1};
		 Sort sort2= new Sort();	 
		 sort2.InitData(arr2);
		 sort2.DCSort();
		 sort2.Show();
		 System.out.println("�Ƚϴ���");
		 System.out.println(sort2.compNum);
		 //��������
		 System.out.println("��������");
		 int arr3[] = {8,7,6,5,4,3,2,1};
		 Sort sort3= new Sort();	 
		 sort3.InitData(arr3);
		 sort3.InsertSort();
		 sort3.Show();
		 System.out.println("�Ƚϴ���");
		 System.out.println(sort3.compNum);
		 //��������
		 System.out.println("��������");
		 int arr4[] = {8,7,6,5,4,3,2,1};
		 Sort sort4= new Sort();	 
		 sort4.InitData(arr4);
		 sort4.InsertSort();
		 sort4.Show();
		 System.out.println("�Ƚϴ���");
		 System.out.println(sort4.compNum);
	 }
}
