package ru.osll.goodtravel.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import ru.osll.goodtravel.enums.TypeOfGroupEnum;

/**
 * Created by mycrfotkai on 16.12.16.
 */

public class PlanService extends RealmObject
{
    @PrimaryKey
    private long id;

    private String name;
    private String Description;
    private int price;
    private String srcToImg;
    private Date date;

    @Ignore
    private TypeOfGroupEnum typeOfGroup;

    public static void addAll(List<Service> serviceList, Realm realm, Date date)
    {
        realm.beginTransaction();
        int id = realm.where(PlanService.class).findAll().max("id").intValue();
        List<PlanService> planServices = new ArrayList<>();

        for(int i = 0; i < serviceList.size(); i++)
        {
            Service service = serviceList.get(i);
            PlanService planService = new PlanService();

            planService.setId(id++);
            planService.setDescription(service.getDescription());
            planService.setName(service.getName());
            planService.setPrice(service.getPrice());
            planService.setSrcToImg(service.getSrcToImg());
            planService.setTypeOfGroup(service.getTypeOfGroup());
            planService.setDate(date);

            planServices.add(planService);
        }
        realm.copyToRealmOrUpdate(planServices);
        realm.commitTransaction();
    }

    public void setTypeOfGroup(TypeOfGroupEnum typeOfGroup)
    {
        this.typeOfGroup = typeOfGroup;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getSrcToImg()
    {
        return srcToImg;
    }

    public void setSrcToImg(String srcToImg)
    {
        this.srcToImg = srcToImg;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public static List<PlanService> getAll(Realm realm)
    {
        return realm.where(PlanService.class).findAll();
    }
}
