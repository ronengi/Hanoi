/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoi;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author stimpy
 */
public class Pegs {
    private ArrayList<Peg> pegs;
    
    public Pegs(int nPegs, int nDisks) {
        pegs = new ArrayList<>();
        for (int i = 0; i < nPegs; ++i) {
            Peg peg = new Peg(nDisks, (i == 0));
            pegs.add(peg);
        }
        System.out.println(this);        
    }

    
    public boolean moveTower(int disk, int src, int dst, int spr) {
        if (disk == 0) {
            move(src, dst);
            return true;
        }

        moveTower(disk - 1, src, spr, dst);
        move(src, dst);
        moveTower(disk - 1, spr, dst, src);

        return true;
    }
    
    
    
    /**
     * Try to move top disk from source peg to destination peg.
     * <br>In case of failure, keeps source peg intact.
     * <br><b>Note: use 0 based peg index values !!!</b>
     * 
     * @param src integer representing the index of source peg
     * @param dst integer representing the index of destination peg
     * 
     * @return boolean representing success value
     */
    public boolean move(int src, int dst) {
        if (src > pegs.size()  ||  dst > pegs.size()) {
            throw(new RuntimeException("move(" + src + ", " + dst + "): No such peg"));
            //return false;       // no such peg
        }
        
        Peg srcPeg = pegs.get(src);
        Peg dstPeg = pegs.get(dst);
        
        // if (srcPeg.getTopDiskLevel() == srcPeg.getSize())
        //     return false;       // no disks in source peg

        if (dstPeg.getTopDiskLevel() == 0) {
            throw(new RuntimeException("move(" + src + ", " + dst + "): no room in target peg"));
            // return false;       // no room in target peg
        }

        int srcDiskSize = srcPeg.removeDisk();

        if (srcDiskSize <= 0)
            return false;       // no disks in source peg
        
        if (!dstPeg.putDisk(srcDiskSize)) {
            srcPeg.putDisk(srcDiskSize); // return disk to src
            throw(new RuntimeException("move(" + src + ", " + dst + "): try to put on smaller disk"));
            // return false;   // try to put on smaller disk
        }

        System.out.println(this);
        
        return true;
    }
    
    
    @Override
    public String toString() {
        if (pegs.isEmpty())
            return "";
        StringBuffer sb = new StringBuffer(100 * pegs.size());
        for (int level = 0; level < (pegs.get(0)).getSize(); ++level) {
            for (int peg = 0; peg < pegs.size(); ++peg) {
                sb.append(pegs.get(peg).getLevel(level));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
        
}
