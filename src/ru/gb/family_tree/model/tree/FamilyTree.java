package ru.gb.family_tree.model.tree;

import ru.gb.family_tree.model.tree.comparators.HumanComparatorByAge;
import ru.gb.family_tree.model.tree.comparators.HumanComparatorByBirthday;
import ru.gb.family_tree.model.tree.comparators.HumanComparatorByName;
import ru.gb.family_tree.model.tree.iterator.FamilyTreeIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<E extends TreeItem<E>> implements Serializable, Iterable<E> {
    private int idHuman;
    public List<E> humanList;

    public FamilyTree(List<E> humanList) {
        this.humanList = humanList;
    }

    public FamilyTree() {
        this(new ArrayList<>());
    }


    public E addHuman(E human) {
        if (!humanList.contains(human)) {
            human.setId(idHuman++);
            humanList.add(human);
            addToParents(human);
            addToKids(human);
            addToPartners(human);
        }
        return human;
    }

    public void addToParents(E human) {
        for (E parent : human.getParents())
            parent.addKid(human);
    }

    public void addToKids(E human) {
        for (E kid : human.getKids()) {
            kid.addParent(human);
        }
    }

    public void addToPartners(E human) {
        for (E partner : human.getPartner()) {
            partner.addPartner(human);
        }
    }

    public E searchHuman(String value) {
        for (E human : humanList) {
            if (human.getSurname().equalsIgnoreCase(value) ||
                    human.getName().equalsIgnoreCase(value) ||
                    human.getNumPassport().equalsIgnoreCase(value)) {
                return human;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {    // переопределили! см FamilyTreeIterator
        return new FamilyTreeIterator<>(humanList);
    }

    public void sortByName() {    // создаем отдельный класс с методом (compare) implements Comparator
        Collections.sort(humanList, new HumanComparatorByName<>());
    }

    public void sortByAge() {
        humanList.sort(new HumanComparatorByAge<>());
    }

    public void sortByBirthday() {    // создаем отдельный класс
        humanList.sort(new HumanComparatorByBirthday<>());
    }


}

