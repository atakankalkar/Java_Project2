
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;
/**
 *
 * @author ataka
 * 
 */
class HW2Exception extends Exception {       //Exception class for insertion and deletion.
public String message;    

    public HW2Exception(String message) {
        this.message=message;
    }

    @Override
    public String toString() {
        
        return message; //To change body of generated methods, choose Tools | Templates.
    } 
}
public class LinkedList extends DLNode implements HW2Interface {     
    private DLNode first;

   
    public void LinkedList(){
        first = null;       
    }
    
    

    @Override
    public void Insert(int newElement, int pos) throws Exception {
        if(pos<0){
             throw LinkedListException();
        }
        DLNode dummy;
        dummy=first;
        if(pos==0){
            DLNode newnode = new DLNode();  
             if(first!=null){         //Insertion to position 0(first pos), when our LinkedList is not empty.
            newnode.Element=newElement;
            newnode.right=first;
            first=newnode;
            newnode.right.left=newnode;     //Provides connection between newnode and pre-existing node.
             }
             else{
               newnode.Element=newElement;   //Insertion to position 0(first pos), when our LinkedList is empty.
            newnode.right=first;
            first=newnode;  
             }
        }
        else{                           
        for (int i = 0; i < pos-1; i++)             
            {  
                  dummy = dummy.right;
                  if (pos > 0 && dummy == null)
                      throw LinkedListException();
            }
        DLNode newnode = new DLNode(); 
        if(dummy.right!=null){       //Insertion to any position except first and last position. 
        newnode.Element=newElement;
        newnode.right=dummy.right;
        newnode.left=dummy;
        dummy.right=newnode;
        newnode.right.left=newnode ;
        }
        else{
        newnode.Element=newElement;      // Just insertion to the last position.
        newnode.right=dummy.right;
        newnode.left=dummy;
        dummy.right=newnode;
        }  
        }
    }
    @Override
    public int Delete(int pos) throws Exception {
      int deleted = 0;
      if(pos<1){        
         throw LinkedListException();      
      }
      DLNode Dummy ; 
      int a = 0; //Variable a defined for control deletion first element and to avoid deletion second element with first element.
        if (pos == 1 && first != null){    //Deletion of first element.
            deleted = first.Element;
            first = first.right;
            a++;
        }
        Dummy = first;
        for (int i = 0; i < pos-2; i++)     //Deletion of any element except first element.
        {
              Dummy = Dummy.right;
              if (pos > 0 && Dummy == null)
                  throw LinkedListException();
        }
        if(a==0){
        deleted =Dummy.right.Element;
        Dummy.right = Dummy.right.right;
        }
        return deleted;
    }

    @Override
    public void ReverseLink() {
        DLNode dummy;
        DLNode atakan;
        dummy=first;
        atakan=first;
         int temp2=0; //temporary variable to swap dummy and atakan's node value.
        while(dummy.right!=null){
            dummy=dummy.right;     //Dummy becomes last node.   
        }
        while(dummy!=atakan && atakan.left!=dummy){  //Dummy and atakan node swaps values .
        temp2=dummy.Element;            //while dummy slide to the left and atakan to the right.
        dummy.Element=atakan.Element;
        atakan.Element=temp2;
        dummy=dummy.left;
        atakan=atakan.right;          
         }   
        
        }


    @Override
    public void SquashL() {
       DLNode dummy;
       dummy=first;
       int count = 1;
       int count2 = 1;
       int total = 1;
       while(dummy.right!=null){    //total variable defined for count how many elements does our linkedlist have.
           total++;
           dummy=dummy.right;
       }
      
       dummy=first;
       while(dummy.right!=null){    
           if(dummy.Element!=dummy.right.Element){
               count2++;
               dummy=dummy.right;
           }
           else
           dummy=dummy.right;
       }
int [] array1element=new int [count2]; //defining array to store  all elements without their repeaters in the LinkedList.
int [] array2element=new int [count2]; //defining array to store how many time the stored elements in array1element repeats itself.
       
       dummy=first;
       int m=0;
    while(dummy.right!=null){        //array1element and array2element are filled.
           if(dummy.Element != dummy.right.Element ){
               array1element[m]=count;
               array2element[m]=dummy.Element;
               count=1; 
               m++;
           }
           else{
               count++;   
           } 
            dummy=dummy.right;
            if(dummy.right==null){           //Special case for last element in the list.
                array1element[m]=count;
                array2element[m]=dummy.Element;
            }
       }
 int newsize=count2*2;            
 int [] array3element = new int [newsize];      //defining new array in order to combine 2 array.
 int k=0;
 for (int z=0;z<newsize-1;z=z+2){
     array3element[z]=array2element[k];
     array3element[z+1]=array1element[k];
     k++;
 }

 
 dummy=first;                           //Reduced the linkedlist to 1 element .
 dummy.right=null;
 dummy.Element = array3element[newsize-1];  //This element is last element of our array3element array.
 
 
 
       
            int i=newsize-2;      //Insertion process for create linkedlist again with using array3element.
            while(i>=0){
           try {
               Insert(array3element[i],0);
           } catch (Exception ex) {
                System.out.println(ex.toString());
           }
                i=i-1;
                
            }
          
        
       
        
    }

    @Override
    public void OplashL() {
        DLNode dummy;
        dummy=first;
        int total =1;
        
        while(dummy.right!=null){         //total variable defined for count how many elements does our linkedlist have.
           total++;
           dummy=dummy.right;
       }
        total=total/2;
  
        //Defining sizearray to store even elements in LinkedList
        int [] sizearray = new int [total];
        dummy=first;
        dummy=dummy.right;
        int a=0;
        while(a<total){
            sizearray[a]=dummy.Element; 
            if(dummy.right==null){

            }
            else{
               dummy=dummy.right.right;
            }
            a++;
        }
                           //Deletion of even elements in LinkedList
        dummy=first;
        while(dummy!=null){                    
            if(dummy.right.right==null){
                dummy.right=null;
            }
            else
            dummy.right=dummy.right.right;        
            
            dummy=dummy.right;
        }
   //The values ​​of the elements in the sizearray are used in the loop as parameters to make the list before the squash function.
      dummy=first;
      int pos=0; 
      for(int i=0;i<total;i++){
          if(sizearray[i]==1){
              pos++;
              dummy=dummy.right;
          }
          else{
              while(sizearray[i]>1){
                  
                  try {
                      Insert(dummy.Element,pos);
                  } catch (Exception ex) {
                      System.out.println(ex.toString());
                  }
                  
                  sizearray[i]=sizearray[i]-1;
                  pos++;
          
              }
              dummy=dummy.right;
              pos++;
          }
      }
    }

    @Override
    public void Output() {
        DLNode dummy;
        dummy = first;
        System.out.print("The Elements in the list are : ");
        while (dummy != null){
            System.out.print(dummy.Element + " " );
            dummy = dummy.right;
        }
        System.out.println("");
        
    }
    @Override
    public void ROutput() {       
         DLNode dummy;
        dummy = first;
        System.out.print("The Reverse Elements in the list are : ");
        while (dummy.right != null){        //Dummy node becomes last node.          
            dummy.right.left=dummy;
            dummy=dummy.right;
            }
         while(dummy !=null){   //While dummy node sliding left all elements are printed until dummy becomes first element.
             System.out.print(dummy.Element + " " );
             dummy=dummy.left;
         }
         System.out.println("");
        }
          
        @Override
 public String toString(){         //New string is defined and fills with linkedlist elements.
     DLNode dummy = first;            //This string refers to LinkedList object(myList).
     String temp="";
     while(dummy!=null){
         temp=temp+dummy.Element+" ";
         dummy=dummy.right;
     }
     return temp;        
 }

        
//new object created from HW2Exception class and toString method is used to print requested words.
    @Override
    public Exception LinkedListException() {   
        HW2Exception hw2 = new HW2Exception("hw2.HW2Exception: Not supported yet.");          
            return hw2;

    
    }
}

 
    

