/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw2;
/**
 *
 * @author ataka
 */
public interface HW2Interface {
 public void Insert(int newElement, int pos) throws Exception;
 public int Delete(int pos) throws Exception;
 public void ReverseLink();
 public void SquashL();
 public void OplashL();
 public void Output();
 public void ROutput();
 @Override
 public String toString();
 public Exception LinkedListException();
    
}
