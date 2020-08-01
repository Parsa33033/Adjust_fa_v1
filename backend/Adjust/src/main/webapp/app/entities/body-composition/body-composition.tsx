import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './body-composition.reducer';
import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBodyCompositionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BodyComposition = (props: IBodyCompositionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { bodyCompositionList, match, loading } = props;
  return (
    <div>
      <h2 id="body-composition-heading">
        <Translate contentKey="adjustApp.bodyComposition.home.title">Body Compositions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.bodyComposition.home.createLabel">Create new Body Composition</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {bodyCompositionList && bodyCompositionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.height">Height</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.weight">Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bmi">Bmi</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bodyCompositionFile">Body Composition File</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bloodTestFile">Blood Test File</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bodyCompositionList.map((bodyComposition, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${bodyComposition.id}`} color="link" size="sm">
                      {bodyComposition.id}
                    </Button>
                  </td>
                  <td>
                    {bodyComposition.createdAt ? (
                      <TextFormat type="date" value={bodyComposition.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{bodyComposition.height}</td>
                  <td>{bodyComposition.weight}</td>
                  <td>{bodyComposition.bmi}</td>
                  <td>
                    {bodyComposition.bodyCompositionFile ? (
                      <div>
                        {bodyComposition.bodyCompositionFileContentType ? (
                          <a onClick={openFile(bodyComposition.bodyCompositionFileContentType, bodyComposition.bodyCompositionFile)}>
                            <img
                              src={`data:${bodyComposition.bodyCompositionFileContentType};base64,${bodyComposition.bodyCompositionFile}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {bodyComposition.bodyCompositionFileContentType}, {byteSize(bodyComposition.bodyCompositionFile)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {bodyComposition.bloodTestFile ? (
                      <div>
                        {bodyComposition.bloodTestFileContentType ? (
                          <a onClick={openFile(bodyComposition.bloodTestFileContentType, bodyComposition.bloodTestFile)}>
                            <img
                              src={`data:${bodyComposition.bloodTestFileContentType};base64,${bodyComposition.bloodTestFile}`}
                              style={{ maxHeight: '30px' }}
                            />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {bodyComposition.bloodTestFileContentType}, {byteSize(bodyComposition.bloodTestFile)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bodyComposition.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bodyComposition.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bodyComposition.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.bodyComposition.home.notFound">No Body Compositions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ bodyComposition }: IRootState) => ({
  bodyCompositionList: bodyComposition.entities,
  loading: bodyComposition.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BodyComposition);
