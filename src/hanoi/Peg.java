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
public class Peg {
    private ArrayList<Integer> peg;

    public Peg(int nDisks, boolean full) {
        peg = new ArrayList<>();
        for (int i = 1; i <= nDisks; ++i) {
            if (full)
                peg.add(i-1, i);
            else
                peg.add(i-1, 0);
        }
    }

    /**
     * Get the number of levels in peg.
     * @return integer representing the total number of levels in peg
     */
    public int getSize() {
        return peg.size();
    }

    /**
     * Get the size of top disk.
     * <br>If no disks return 0.
     * 
     * @return an integer indicating disk size or 0 if no disk
     */
    public int getTopDiskSize() {
        Iterator<Integer> pegIterator = peg.iterator();
        int diskSize = 0;
        while (pegIterator.hasNext()  &&  diskSize == 0) {
            diskSize = pegIterator.next();
        }
        return diskSize;
    }

    /**
     * Find the first level index with disk on.
     * <br>If no disks in peg, returns size().
     * <p>
     * 0 == top level.<br>
     * size() - 1 == bottom level.<br>
     * size() == one level below bottom level.<br>
     * </p>
     * 
     * @return an integer indicating the index of the topmost level containing a disk
     */
    public int getTopDiskLevel() {
        for (int level = 0; level < peg.size(); ++level) {
            if (peg.get(level) > 0)
                return level;
        }
        return peg.size();
    }

    
    /**
     * Try to put a disk on top of disks in this peg.
     * 
     * @param diskSize
     * @return boolean indicating success or failure
     */
    public boolean putDisk(int diskSize) {
        int topDiskLevel = getTopDiskLevel();
        if (topDiskLevel == 0) {
            throw(new RuntimeException("putDisk(" + diskSize + "): peg full"));
            //return false;       // peg full
        }
        if (getTopDiskSize() > 0  &&  getTopDiskSize() <= diskSize) {
            throw(new RuntimeException("putDisk(" + diskSize + "): try to put on smaller disk"));
            // return false;       // try to put on smaller disk
        }
        peg.set(topDiskLevel - 1, diskSize);
        return true;
    }

    /**
     * Try to remove top disk in this peg.
     * @return integer indicating the removed disk size, or 0 if no disks
     */
    public int removeDisk() {
        int topDiskSize = getTopDiskSize();
        if (topDiskSize > 0)
            peg.set(getTopDiskLevel(), 0);
        return topDiskSize;
    }
    

    /**
     * Return a String representation of a level
     * @param nLevel an integer indicating the level index to be represented
     * @return a String representation of the level
     */
    public String getLevel(int nLevel) {
        int diskSize = (peg.get(nLevel) * 2) - 1;
        int maxSize = (peg.size() + 5) * 2 - 1;
        int spaceSize;
        
        if (diskSize > 0)
            spaceSize = (maxSize - diskSize) / 2;
        else
            spaceSize = (maxSize - 1) / 2;

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < spaceSize; ++i)
            sb.append(' ');

        if (diskSize > 0) {
            for (int i = 0; i < diskSize; ++i)
                sb.append('*');
        }
        else
            sb.append('|');
        
        for (int i = 0; i < spaceSize; ++i)
            sb.append(' ');
        
        return sb.toString();
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(10 * peg.size());
        for (int i = 0; i < peg.size(); ++i)
            sb.append(getLevel(i)).append("\n");
        return sb.toString();
    }
    
}
