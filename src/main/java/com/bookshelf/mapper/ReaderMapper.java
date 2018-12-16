package com.bookshelf.mapper;
import io.swagger.model.ReadersListModel;

import com.bookshelf.entity.Reader;
import io.swagger.model.ReaderModel;
import io.swagger.model.AddReaderRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ReaderMapper {
    private ReaderMapper() {}


    public static Reader mapAddReaderRequestToReader(AddReaderRequest request) {
        Reader reader = new Reader();
        reader.setName(reader.getName());
        return reader;
    }

    public static ReaderModel mapReaderToReaderModel(Reader reader) {
        ReaderModel readerModel = new ReaderModel();
        readerModel.setName(reader.getName());
        return readerModel;
    }

    public static ReadersListModel mapReadersToReadersListModel(List<Reader> readers) {
        ReadersListModel readersListModel = new ReadersListModel();
        List<ReaderModel> bookModels = readers.stream().map(reader -> {
            ReaderModel readerModel = new ReaderModel();
            readerModel.setName(reader.getName());
            return readerModel;
        }).collect(Collectors.toList());
        readersListModel.setItems(bookModels);
        return readersListModel;
    }
}
