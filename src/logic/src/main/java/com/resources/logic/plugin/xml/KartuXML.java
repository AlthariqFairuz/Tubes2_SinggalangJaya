package com.resources.logic.plugin.xml;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import com.resources.logic.plugin.xml.ItemXML;

public class KartuXML {
    private String lokasi;
    private String kartu;
    private int umur;
    @JacksonXmlProperty(localName = "JumlahItemEfektif")
    private int jumlahItemEfektif;
    private List<ItemXML> itemXMLAktif;

    private String getLokasi() {
        return lokasi;
    }

    private String getKartu() {
        return kartu;
    }

    private int getUmur() {
        return umur;
    }

    private int getJumlahItemEfektif() {
        return jumlahItemEfektif;
    }

    private List<ItemXML> getItemAktif() {
        return itemXMLAktif;
    }

}
